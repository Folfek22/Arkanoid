

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.awt.image.VolatileImage;
import java.util.HashMap;

public class VolatileImageResolver {

	private static final HashMap<BufferedImage, VolatileImage> IMAGES_MAP = new HashMap<BufferedImage, VolatileImage>();

	public static VolatileImage fromBufferedImage(final BufferedImage source) {
		if (!IMAGES_MAP.containsKey(source)) {
			IMAGES_MAP.put(source, getVolatileImage(source));
		}
		VolatileImage target = IMAGES_MAP.get(source);
		if (target == null) {
			IMAGES_MAP.put(source, getVolatileImage(source));
			target = IMAGES_MAP.get(source);
		}
		return target;
	}

	private static VolatileImage createVolatileImage(final int width, final int height, final int transparency) {
		final GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		final GraphicsConfiguration gc = ge.getDefaultScreenDevice().getDefaultConfiguration();
		VolatileImage image = null;

		image = gc.createCompatibleVolatileImage(width, height, transparency);

		final int valid = image.validate(gc);

		if (valid == VolatileImage.IMAGE_INCOMPATIBLE) {
			image = createVolatileImage(width, height, transparency);
			return image;
		}

		return image;
	}

	public static VolatileImage getVolatileImage(final BufferedImage bImage) {
		final VolatileImage image = createVolatileImage(bImage.getWidth(), bImage.getHeight(), Transparency.OPAQUE);
		Graphics2D g = null;
		try {
			g = image.createGraphics();
			g.drawImage(bImage, null, 0, 0);
		} finally {
			g.dispose();
		}
		return image;
	}

	public static VolatileImage getVolatileImageWithTransparency(final BufferedImage bImage) {
		final VolatileImage image = createVolatileImage(bImage.getWidth(), bImage.getHeight(), Transparency.TRANSLUCENT);
		Graphics2D g = null;
		try {
			g = image.createGraphics();
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_IN));
			g.drawImage(bImage, null, 0, 0);
		} finally {
			g.dispose();
		}
		return image;
	}
}
