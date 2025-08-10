package org.firstinspires.ftc.teamcode.OpModes.Autonomous.Tests;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

import java.util.ArrayList;
import java.util.List;

/**
 * An EasyOpenCV pipeline to detect blue, red, and yellow samples using color masking in HSV.
 */
public class ColorDetectionPipeline extends OpenCvPipeline {

	// Output frame for rendering detections
	private Mat outputFrame;

	// Mats for processing
	private Mat hsvFrame;
	private Mat redMask;
	private Mat blueMask;
	private Mat yellowMask;
	private Mat redMask2; // For wrapping red hue value around 0/180

	// Storing the detected color and its bounding box
	private volatile DetectedColor detectedColor = DetectedColor.NONE;
	private volatile Rect detectedRect = null;
	private volatile double detectedArea = 0;

	// Enum to represent the detected color
	public enum DetectedColor {
		RED,
		BLUE,
		YELLOW,
		NONE
	}

	// HSV color ranges for red, blue, and yellow
	// NOTE: These values might need to be tuned for your specific robot camera and lighting conditions.
	// Use the HSV Calibration tool in EasyOpenCV to find the best values.

	// Blue range
	private static final Scalar BLUE_LOWER_HSV = new Scalar(100, 150, 100);
	private static final Scalar BLUE_UPPER_HSV = new Scalar(120, 255, 255);

	// Red range (Note: Red hue values wrap around 0 and 180)
	private static final Scalar RED_LOWER_HSV1 = new Scalar(0, 150, 100);
	private static final Scalar RED_UPPER_HSV1 = new Scalar(10, 255, 255);
	private static final Scalar RED_LOWER_HSV2 = new Scalar(160, 150, 100);
	private static final Scalar RED_UPPER_HSV2 = new Scalar(180, 255, 255);

	// Yellow range
	private static final Scalar YELLOW_LOWER_HSV = new Scalar(20, 100, 100);
	private static final Scalar YELLOW_UPPER_HSV = new Scalar(35, 255, 255);

	/**
	 * The main processing method of the pipeline. It is called for each frame from the camera.
	 * @param input The input frame from the camera.
	 * @return The frame to be displayed in the preview, with detected objects marked.
	 */
	@Override
	public Mat processFrame(Mat input) {
		// Initialize our Mats if they haven't been already
		if (outputFrame == null) {
			outputFrame = new Mat();
			hsvFrame = new Mat();
			redMask = new Mat();
			blueMask = new Mat();
			yellowMask = new Mat();
			redMask2 = new Mat();
		}

		// Clone the input to create an output frame we can draw on without modifying the original
		input.copyTo(outputFrame);

		// Convert the input frame to the HSV color space for easier color detection
		Imgproc.cvtColor(input, hsvFrame, Imgproc.COLOR_RGB2HSV);

		// --- Create color masks for each target color ---

		// Blue mask
		Core.inRange(hsvFrame, BLUE_LOWER_HSV, BLUE_UPPER_HSV, blueMask);

		// Red mask (combining two ranges due to hue wrap-around)
		Core.inRange(hsvFrame, RED_LOWER_HSV1, RED_UPPER_HSV1, redMask);
		Core.inRange(hsvFrame, RED_LOWER_HSV2, RED_UPPER_HSV2, redMask2);
		Core.bitwise_or(redMask, redMask2, redMask);

		// Yellow mask
		Core.inRange(hsvFrame, YELLOW_LOWER_HSV, YELLOW_UPPER_HSV, yellowMask);

		// --- Find contours and the largest one for each color ---
		// A contour is a curve joining all continuous points (along the boundary),
		// having the same color or intensity.

		MatOfPoint largestRedContour = findLargestContour(redMask);
		MatOfPoint largestBlueContour = findLargestContour(blueMask);
		MatOfPoint largestYellowContour = findLargestContour(yellowMask);

		// --- Determine which color has the largest detected area and update state ---
		detectedColor = DetectedColor.NONE;
		detectedRect = null;
		detectedArea = 0;

		Rect redRect = null;
		if (largestRedContour != null) {
			redRect = Imgproc.boundingRect(largestRedContour);
			double redArea = Imgproc.contourArea(largestRedContour);
			if (redArea > detectedArea) {
				detectedColor = DetectedColor.RED;
				detectedRect = redRect;
				detectedArea = redArea;
			}
		}

		Rect blueRect = null;
		if (largestBlueContour != null) {
			blueRect = Imgproc.boundingRect(largestBlueContour);
			double blueArea = Imgproc.contourArea(largestBlueContour);
			if (blueArea > detectedArea) {
				detectedColor = DetectedColor.BLUE;
				detectedRect = blueRect;
				detectedArea = blueArea;
			}
		}

		Rect yellowRect = null;
		if (largestYellowContour != null) {
			yellowRect = Imgproc.boundingRect(largestYellowContour);
			double yellowArea = Imgproc.contourArea(largestYellowContour);
			if (yellowArea > detectedArea) {
				detectedColor = DetectedColor.YELLOW;
				detectedRect = yellowRect;
				detectedArea = yellowArea;
			}
		}

		// --- Draw a bounding box around the detected object for visual feedback ---
		if (detectedRect != null) {
			Imgproc.rectangle(outputFrame, detectedRect, new Scalar(0, 255, 0), 2);
			Imgproc.putText(outputFrame, detectedColor.toString(), detectedRect.tl(), Imgproc.FONT_HERSHEY_SIMPLEX, 1.0, new Scalar(0, 255, 0), 2);
		}

		// Return the output frame with the detections drawn on it
		return outputFrame;
	}

	/**
	 * Finds the largest contour in a given binary image mask.
	 * @param mask The binary image mask.
	 * @return The largest contour as a MatOfPoint, or null if no contours are found.
	 */
	private MatOfPoint findLargestContour(Mat mask) {
		List<MatOfPoint> contours = new ArrayList<>();
		Imgproc.findContours(mask, contours, new Mat(), Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);

		if (contours.isEmpty()) {
			return null;
		}

		MatOfPoint largestContour = contours.get(0);
		double maxArea = Imgproc.contourArea(largestContour);

		for (MatOfPoint contour : contours) {
			double area = Imgproc.contourArea(contour);
			if (area > maxArea) {
				maxArea = area;
				largestContour = contour;
			}
		}
		return largestContour;
	}

	/**
	 * Returns the currently detected color.
	 * @return An enum representing the detected color.
	 */
	public DetectedColor getDetectedColor() {
		return detectedColor;
	}

	/**
	 * Returns the bounding rectangle of the detected object.
	 * @return A Rect object, or null if no object is detected.
	 */
	public Rect getDetectedRect() {
		return detectedRect;
	}
}

