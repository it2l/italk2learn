package com.italk2learn.tis;

public class FeedbackData {
	
	public static int affectBoosts = 1;
	public static int nextStep=2;
	public static int problemSolving=3;
	public static int reflection=4;
	public static int mathsVocabular= 5;
	public static int talkAloud = 6;
	public static int affirmation = 7;
	public static int taskNotFinished = 8;

	public static final String[] talkAloudMessage = {"Please explain what you are doing.",
		"How do you feel about this task?", "What do you think about this task? Is it easy or hard?",
		"Please explain what you're aiming to do.", "Please explain what you're going to do next. You might find it helpful to read the task again."};
	
	public static final String[] reflectiveTask = {"Have you read the task? What are you asked to do?",
		"Please read the task again. What are you asked to do?"};
	
	public static final String reflectiveForflow = "Well done, you've have worked hard on this task. Why did you use this method?";
	public static final String reflectiveForConfusion = "This is quite tricky. What is the task asking you to do?";
	public static final String reflectiveForFrustration = "When things don't work out as expected, it can be annoying! What are you trying to do?";
	
	public static final String[] affectBoostsForConfusion = {"You seem like you're trying hard. That's excellent!",
		"Have another go. If you think hard about the problem, you'll soon work out what to do. Well done!",
		"Look at the task again. If you keep working hard, you'll soon make progress. Nice one!"};
	
	public static final String[] affectBoostsForFrustration = {"It's annoying when things don't work out as you expect. Have another go and see if you can work out what to do",
		"It doesn't always makes sense straight away. Try again and see if you can move forward.",
		"Fractions can be frustrating. What can you remember about denominators and numerators?"};
	
	public static final String[] affectBoostsForBoredom = {"Are you finding this too easy? Perhaps you should quickly finish this task, so you can tackle a more challenging task.",
		"Fractions aren't always fun. Can you think where you might use fractions outside of school?",
		"Do you think fractions are a bit boring? Once you've had lots of practice, you'll probably find them easy to do.",
		"Can you think of a different way of answering this task, to make it more challenging for you?"};
	
	public static String mathsReminder = "Please explain that again using the words denominator and numerator.";
	
	public static final String[] talkAloudMessageGerman = {"Erkl�re bitte, was du gerade tust.",
		"Wie f�hlst du dich mit dieser Aufgabe?", "Was denkst du �ber diese Aufgabe: Findest du  sie einfach oder schwierig?",
		"Erkl�re bitte, welches Ziel du verfolgst.", "Erkl�re bitte, was du als n�chstes tun wirst."};
	
	public static final String[] reflectiveTaskGerman = {"Hast du die Aufgabe gelesen? Was sollst du hier tun?",
		"Bitte lies die Aufgabe erneut. Was sollst du hier tun?"};
	
	public static final String reflectiveForflowGerman = "Gut gemacht! Du hast hartn�ckig an dieser Aufgabe gearbeitet! Warum bist du so vorgegangen?";
	public static final String reflectiveForConfusionGerman = "Diese Aufgabe ist ziemlich knifflig! Was sollst du bei dieser Aufgabe machen?";
	public static final String reflectiveForFrustrationGerman = "Manchmal ist es �rgerlich, wenn die Dinge nicht so laufen wie man erwartet! Was versuchst du gerade zu tun?";
	
	public static final String[] affectBoostsForConfusionGerman = {"Du gibst dir sehr viel M�he! Das ist sehr gut!",
		"Versuche es noch einmal. Wenn du gr�ndlich nachdenkst, wirst du die Aufgabe bald schaffen. Du schaffst das!",
		"Sieh dir die Aufgabe erneut an. Wenn du gr�ndlich arbeitest, wirst du schon bald Fortschritte machen. Gut gemacht!"};
	
	public static final String[] affectBoostsForFrustrationGerman = {"Es ist �rgerlich, wenn Dinge nicht so klappen, wie man es erwartet. Versuche es noch einmal und �berlege dir, wie du weiter vorgehen kannst.",
		"Auch wenn es auf den ersten Blick noch keinen Sinn f�r dich ergibt, versuche es weiterhin.",
		"Manchmal kann Rechnen mit Br�chen frustrierend sein. �berlege dennoch, was du schon alles �ber Z�hler und Nenner gelernt hast"};
	
	public static final String[] affectBoostsForBoredomGerman = {"Ist dir diese Aufgabe zu einfach? Vielleicht kannst du diese Aufgabe schnell erledigen, damit du dann eine schwierigere Aufgabe machen kannst.",
		"Auch wenn das Rechnen mit Br�chen nicht immer Spa� macht, kannst du dir vorstellen, wo man Br�che auch au�erhalb der Schule benutzen k�nnte?",
		"Findest du, dass Br�che ein wenig langweilig sind? Aber wenn du viel ge�bt hast, wirst du sie sicher einfach finden.",
		"Vielleicht kannst du die Aufgabe etwas herausfordernder gestalten, indem du einen anderen L�sungsweg f�r diese Aufgabe findest."};
	
	public static String mathsReminderGerman = "Bitte erkl�re das noch einmal und benutze die Worte 'Z�hler' und 'Nenner'.";
	
	public static final String[] talkAloudMessageSpanish = {"SPANISH Please explain what you are doing.",
		"SPANISH How do you feel about this task?", "SPANISH What do you think about this task? Is it easy or hard?",
		"SPANISH Please explain what you're aiming to do.", "SPANISH Please explain what you're going to do next."};
	
	public static final String[] reflectiveTaskSpanish = {"SPANISH Have you read the task? What are you asked to do?",
		"SPANISH Please read the task again. What are you asked to do?"};
	
	public static final String reflectiveForflowSpanish = "SPANISH Well done, you've have worked hard on this task. Why did you use this method?";
	public static final String reflectiveForConfusionSpanish = "SPANISH This is quite tricky. What is the task asking you to do?";
	public static final String reflectiveForFrustrationSpanish = "SPANISH When things don't work out as expected, it can be annoying! What are you trying to do?";
	
	public static final String[] affectBoostsForConfusionSpanish = {"SPANISH You seem like you're trying hard. That's excellent!",
		"SPANISH Have another go. If you think hard about the problem, you'll soon work out what to do. Well done!",
		"SPANISH Look at the task again. If you keep working hard, you'll soon make progress. Nice one!"};
	
	public static final String[] affectBoostsForFrustrationSpanish = {"SPANISH It's annoying when things don't work out as you expect. Have another go and see if you can work out what to do",
		"SPANISH It doesn't always makes sense straight away. Try again and see if you can move forward.",
		"SPANISH Fractions can be frustrating. What can you remember about denominators and numerators?"};
	
	public static final String[] affectBoostsForBoredomSpanish = {"SPANISH Are you finding this too easy? Perhaps you should quickly finish this task, so you can tackle a more challenging task.",
		"SPANISH Fractions aren't always fun. Can you think where you might use fractions outside of school?",
		"SPANISH Do you think fractions are a bit boring? Once you've had lots of practice, you'll probably find them easy to do.",
		"SPANISH Can you think of a different way of answering this task, to make it more challenging for you?"};
	
	public static String mathsReminderSpanish = "SPANISH Please explain that again using the words denominator and numerator.";
	
}