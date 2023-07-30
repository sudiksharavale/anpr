import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class TopContourViewer {

	public TopContourViewer(){
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new JFrame("Top Contour Image");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				BufferedImage img = null;
				
				try {
					File input = new File("src/output/6. topcontour.jpg");
			        img = ImageIO.read(input);
			        System.out.println("Top contour image created");
					System.out.println();
				}
				
				catch (IOException e){
					e.printStackTrace();
					System.exit(1);
				}
				
				ImageIcon imgIcon = new ImageIcon(img);
				JLabel lbl = new JLabel();
				lbl.setIcon(imgIcon);
				frame.getContentPane().add(lbl, BorderLayout.CENTER);
				frame.pack();
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			}
		});
	}
}
