import java.io.IOException;
import java.net.URL;

import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.w3c.dom.css.Rect;

public class ANPR {

    public static void main(String[] args) {
        String imageUrl = "https://english.cdn.zeenews.com/sites/default/files/styles/zm_700x400/public/2022/01/10/1004194-bh-plate.jpg";

        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        try {
            Mat inputImage = Imgcodecs.imread(new URL(imageUrl).getPath());

            Mat grayscaleImage = convertToGrayscale(inputImage);

            Mat detectedPlate = detectNumberPlate(grayscaleImage);

            Imgcodecs.imwrite("detected_plate.jpg", detectedPlate);

            System.out.println("Number Plate Detected and Saved!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Mat convertToGrayscale(Mat colorImage) {
        Mat grayscaleImage = new Mat();
        Imgproc.cvtColor(colorImage, grayscaleImage, Imgproc.COLOR_BGR2GRAY);
        return grayscaleImage;
    }

    public static Mat detectNumberPlate(Mat grayscaleImage) {
        CascadeClassifier plateCascade = new CascadeClassifier("path/to/your/haarcascade_plate.xml");
        MatOfRect plates = new MatOfRect();

        plateCascade.detectMultiScale(grayscaleImage, plates, 1.1, 5, 0, new Size(100, 40), new Size(300, 120));

        for (Rect rect : plates.toArray()) {
            Imgproc.rectangle(grayscaleImage, rect.tl(), rect.br(), new Scalar(0, 0, 255), 2);
        }

        return grayscaleImage;
    }
}
