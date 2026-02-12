package ca.quines.inabox.helper.content;

import java.util.List;
import java.util.Locale;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

@Service
public class ContentServiceProvider {

	private final List<ContentHelperService> services;

	public ContentServiceProvider(List<ContentHelperService> services) {
		this.services = services;
	}

	public ContentHelperService getService() {
		Locale currentLocale = LocaleContextHolder.getLocale();
		return services.stream()
				.filter(s -> s.supports(currentLocale))
				.findFirst()
				.orElse(services.get(0)); // Fallback to default
		// For notes on fallback, see the class notes on ca.quines.inabox.helper.content.EnglishHelper.
	}

}
