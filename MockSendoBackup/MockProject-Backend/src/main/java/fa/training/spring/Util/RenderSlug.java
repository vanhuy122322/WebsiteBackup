package fa.training.spring.Util;

import java.util.Random;

import com.github.slugify.Slugify;

public class RenderSlug {
	public static String renderSlug(String value) {
		return new Slugify().slugify(value + "-" + new Random().nextInt(9999999));
	}
}
