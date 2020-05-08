package com.translator.Tranlator;

import java.io.IOException;
import java.net.URISyntaxException;

import com.google.api.translate.Language;


public class App {

	private static Translator translator;

	public static void main(String[] args) throws IOException, URISyntaxException {

		if (args.length != 1) {

			System.out.println("Google API key must be passed as the first and last argument");

			System.exit(1);

		}

		translator = new Translator(args[0]);

		try {

			testLanguages();

			testTranslate();

			testDetect();

		} catch (TranslatorException e) {

			System.out.println("Google Translate API returned an error " + e.getMessage());

			e.printStackTrace();

		} finally {

			translator.close();

		}

	}

	public static void testLanguages() throws Exception {

		Language[] languages = translator.languages("en"); // Returns a list of supported languages with the language
															// name in English

		System.out.println("languages = " + Arrays.toString(languages));

		// OUTPUT: languages = [Language{language='af', name='Afrikaans'},
		// Language{language='sq', name='Albanian'}, Language{language='ar',
		// name='Arabic'}, Language{language='be', name='Belarusian'},
		// Language{language='bg', name='Bulgarian'}, Language{language='ca',
		// name='Catalan'}, Language{language='zh', name='Chinese (Simplified)'},
		// Language{language='zh-TW', name='Chinese (Traditional)'},
		// Language{language='hr', name='Croatian'}, Language{language='cs',
		// name='Czech'}, Language{language='da', name='Danish'},
		// Language{language='nl', name='Dutch'}, Language{language='en',
		// name='English'}, Language{language='et', name='Estonian'},
		// Language{language='tl', name='Filipino'}, Language{language='fi',
		// name='Finnish'}, Language{language='fr', name='French'},
		// Language{language='gl', name='Galician'}, Language{language='de',
		// name='German'}, Language{language='el', name='Greek'},
		// Language{language='ht', name='Haitian Creole'}, Language{language='iw',
		// name='Hebrew'}, Language{language='hi', name='Hindi'},
		// Language{language='hu', name='Hungarian'}, Language{language='is',
		// name='Icelandic'}, Language{language='id', name='Indonesian'},
		// Language{language='ga', name='Irish'}, Language{language='it',
		// name='Italian'}, Language{language='ja', name='Japanese'},
		// Language{language='ko', name='Korean'}, Language{language='lv',
		// name='Latvian'}, Language{language='lt', name='Lithuanian'},
		// Language{language='mk', name='Macedonian'}, Language{language='ms',
		// name='Malay'}, Language{language='mt', name='Maltese'},
		// Language{language='no', name='Norwegian'}, Language{language='fa',
		// name='Persian'}, Language{language='pl', name='Polish'},
		// Language{language='pt', name='Portuguese'}, Language{language='ro',
		// name='Romanian'}, Language{language='ru', name='Russian'},
		// Language{language='sr', name='Serbian'}, Language{language='sk',
		// name='Slovak'}, Language{language='sl', name='Slovenian'},
		// Language{language='es', name='Spanish'}, Language{language='sw',
		// name='Swahili'}, Language{language='sv', name='Swedish'},
		// Language{language='th', name='Thai'}, Language{language='tr',
		// name='Turkish'}, Language{language='uk', name='Ukrainian'},
		// Language{language='vi', name='Vietnamese'}, Language{language='cy',
		// name='Welsh'}, Language{language='yi', name='Yiddish'}]

	}

	public static void testTranslate() throws IOException, URISyntaxException, TranslatorException {

		Translation fromEnglish = translator.translate("I", "en", "es");

		System.out.println("'I' in en = '" + fromEnglish.getTranslatedText() + "' in es");

		Translation fromUnknown = translator.translate("I", null, "es");

		System.out.println("'I' in " + fromUnknown.getDetectedSourceLanguage() + " = '"
				+ fromUnknown.getTranslatedText() + "' in es");

		String[] sourceTexts = { "I", "a" };

		Translation[] translations = translator.translate(sourceTexts, null, "es");

		for (int i = 0, sourceTextsLength = sourceTexts.length; i < sourceTextsLength; i++) {

			System.out.println(
					"'" + sourceTexts[i] + "' in en = " + "'" + translations[i].getTranslatedText() + "' in es");

		}

	}

	private static void testDetect() throws IOException, URISyntaxException, TranslatorException {

		Detection[][] detections = translator.detect(new String[] { "I", "We" });

		System.out.println("detections = " + Arrays.deepToString(detections));

	}

}