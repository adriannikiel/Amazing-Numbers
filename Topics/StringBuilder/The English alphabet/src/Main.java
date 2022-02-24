class EnglishAlphabet {

    public static StringBuilder createEnglishAlphabet() {
        // write your code here

        char firstLetter = 'A';
        char lastLetter = 'Z';

        char processedLetter = firstLetter;

        StringBuilder builder = new StringBuilder();

        while (processedLetter < lastLetter) {

            builder.append(processedLetter).append(" ");
            processedLetter++;
        }

        builder.append(processedLetter);

        return builder;
    }
}