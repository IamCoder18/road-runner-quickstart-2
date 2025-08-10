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

public class ColorDetectionPipeline extends OpenCvPipeline {

	private Mat outputFrame;

	private Mat hsvFrame;
	private Mat redMask;
	private Mat blueMask;
	private Mat yellowMask;
	private Mat redMask2;

	private volatile List<Rect> blueRects = new ArrayList<>();
	private volatile List<Rect> redRects = new ArrayList<>();
	private volatile List<Rect> yellowRects = new ArrayList<>();

	private static final double MIN_CONTOUR_AREA = 1000;

	public enum DetectedColor {
		RED,
		BLUE,
		YELLOW,
		NONE
	}

	private static final Scalar BLUE_LOWER_HSV = new Scalar(100, 150, 100);
	private static final Scalar BLUE_UPPER_HSV = new Scalar(120, 255, 255);

	private static final Scalar RED_LOWER_HSV1 = new Scalar(0, 150, 100);
	private static final Scalar RED_UPPER_HSV1 = new Scalar(10, 255, 255);
	private static final Scalar RED_LOWER_HSV2 = new Scalar(160, 150, 100);
	private static final Scalar RED_UPPER_HSV2 = new Scalar(180, 255, 255);

	private static final Scalar YELLOW_LOWER_HSV = new Scalar(25, 150, 150);
	private static final Scalar YELLOW_UPPER_HSV = new Scalar(35, 255, 255);

	@Override
	public Mat processFrame(Mat input) {
		if (outputFrame == null) {
			outputFrame = new Mat();
			hsvFrame = new Mat();
			redMask = new Mat();
			blueMask = new Mat();
			yellowMask = new Mat();
			redMask2 = new Mat();
		}

		input.copyTo(outputFrame);

		Imgproc.cvtColor(input, hsvFrame, Imgproc.COLOR_RGB2HSV);

		Core.inRange(hsvFrame, BLUE_LOWER_HSV, BLUE_UPPER_HSV, blueMask);
		Core.inRange(hsvFrame, RED_LOWER_HSV1, RED_UPPER_HSV1, redMask);
		Core.inRange(hsvFrame, RED_LOWER_HSV2, RED_UPPER_HSV2, redMask2);
		Core.bitwise_or(redMask, redMask2, redMask);
		Core.inRange(hsvFrame, YELLOW_LOWER_HSV, YELLOW_UPPER_HSV, yellowMask);

		redRects.clear();
		blueRects.clear();
		yellowRects.clear();

		findContoursAndRects(redMask, redRects, new Scalar(255, 0, 0), "RED");
		findContoursAndRects(blueMask, blueRects, new Scalar(0, 0, 255), "BLUE");
		findContoursAndRects(yellowMask, yellowRects, new Scalar(255, 255, 0), "YELLOW");

		return outputFrame;
	}

	private void findContoursAndRects(Mat mask, List<Rect> rectsList, Scalar color, String text) {
		List<MatOfPoint> contours = new ArrayList<>();
		Imgproc.findContours(mask, contours, new Mat(), Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);

		for (MatOfPoint contour : contours) {
			double area = Imgproc.contourArea(contour);
			if (area > MIN_CONTOUR_AREA) {
				Rect rect = Imgproc.boundingRect(contour);
				rectsList.add(rect);
				Imgproc.rectangle(outputFrame, rect, color, 2);
				Imgproc.putText(outputFrame, text, rect.tl(), Imgproc.FONT_HERSHEY_SIMPLEX, 0.8, color, 2);
			}
		}
	}

	public List<Rect> getBlueRects() {
		return blueRects;
	}

	public List<Rect> getRedRects() {
		return redRects;
	}

	public List<Rect> getYellowRects() {
		return yellowRects;
	}
}
