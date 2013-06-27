package com.spd.cq.searspartsdirect.common.helpers;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum Singularizer {
	INSTANCE;
	
	private static abstract class SingularizationStrategy {
		public abstract String singularize(String pluralForm);
	}
	private static class Identity extends SingularizationStrategy {
		public String singularize(String pluralForm) {
			return pluralForm;
		}
	}
	private static class RemoveTrailing extends SingularizationStrategy {
		private final String trailing;
		
		public RemoveTrailing(String trailing) {
			this.trailing = trailing;
		}
		
		public String singularize(String pluralForm) {
			return pluralForm.substring(0,pluralForm.length() - trailing.length());
		}
	}
	private static class ChangeTrailingTo extends RemoveTrailing {
		private final String to;
		
		public ChangeTrailingTo(String trailing, String to) {
			super(trailing);
			this.to = to;
		}
		
		public String singularize(String pluralForm) {
			return super.singularize(pluralForm) + to;
		}
	}
	private static class Compound extends SingularizationStrategy {
		private final Singularizer singularizer;
		private final Pattern splitPattern;
		
		public Compound(Singularizer singularizer, Pattern splitPattern) {
			this.singularizer = singularizer;
			this.splitPattern = splitPattern;
		}
		
		public String singularize(String pluralForm) {
			Matcher splitter = splitPattern.matcher(pluralForm);
			StringBuilder singulars = new StringBuilder();
			int end = 0;
			while (splitter.find()) {
				singulars.append(singularizer.singularizeEnglish(pluralForm.substring(end,splitter.start())));
				end = splitter.end();
				singulars.append(splitter.group());
			}
			singulars.append(singularizer.singularizeEnglish(pluralForm.substring(end,pluralForm.length())));
			return singulars.toString();
		}
	}
	
	private static Map<Pattern,SingularizationStrategy> englishStrategies = initEnglish();
	private static final Map<Pattern,SingularizationStrategy> initEnglish() {
		LinkedHashMap<Pattern,SingularizationStrategy> strategies = new LinkedHashMap<Pattern,SingularizationStrategy>();
		
		Pattern splitPattern = Pattern.compile(" *[/,\\&] *");
		strategies.put(splitPattern,new Compound(Singularizer.INSTANCE,splitPattern));
		strategies.put(Pattern.compile("[^s]$"),new Identity());
		SingularizationStrategy removeTrailingS = new RemoveTrailing("s");
		SingularizationStrategy removeTrailingEs = new RemoveTrailing("es");
		strategies.put(Pattern.compile("[^r]ives$"),new ChangeTrailingTo("ves","fe"));
		strategies.put(Pattern.compile("[cs]hes$"),removeTrailingEs);
		strategies.put(Pattern.compile("sses$"),removeTrailingEs);
		strategies.put(Pattern.compile("[gchklnrstv]es$"),removeTrailingS);
		strategies.put(Pattern.compile("isseries$"),removeTrailingS);
		strategies.put(Pattern.compile("ies$"),new ChangeTrailingTo("ies","y"));
		strategies.put(Pattern.compile("s$"),removeTrailingS);
		
		return strategies;
	}
	
	public String singularize(Locale locale, String pluralForm) {
		if (!locale.equals(Locale.ENGLISH)) {
			throw new IllegalArgumentException(locale + " is not supported");
		}
		return singularizeEnglish(pluralForm);
	}
	
	private String singularizeEnglish(String pluralForm) {
		String singular = "";
		for (Map.Entry<Pattern,SingularizationStrategy> strategy: englishStrategies.entrySet()) {
			if (strategy.getKey().matcher(pluralForm).find()) {
				singular = strategy.getValue().singularize(pluralForm);
				break;
			}
		}
		return singular;
	}
}
