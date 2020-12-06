package eu.molenmaker.adventofcode.day02;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordPolicy {
    final static Logger log = LoggerFactory.getLogger(PasswordPolicy.class);

    private char character;
    private int minCount;
    private int maxCount;

    public PasswordPolicy(char character, int minCount, int maxCount) {
        this.character = character;
        this.minCount = minCount;
        this.maxCount = maxCount;
    }



     static PasswordPolicy parsePolicy(String line) {
        Pattern pattern = Pattern.compile("([0-9]+)-([0-9]+)\\s([a-z]+)");
        Matcher matcher = pattern.matcher(line);
        if (matcher.find()) {
            return new PasswordPolicy(matcher.group(3).charAt(0),  NumberUtils.toInt(matcher.group(1)), NumberUtils.toInt(matcher.group(2)));
        }
        throw new IllegalArgumentException("Invalid line " + line);
    }

    public boolean testPassword(String password) {
        int counter = StringUtils.countMatches(password, character);
        log.info(String.format("Found %c %d times in %s", character, counter, password));
        return (counter >= minCount && counter <= maxCount);
    }

    public boolean testPasswordPolicyPart2(String password) {
        int counter = 0;
        if (password.charAt(minCount-1) == character) counter++;
        if (password.charAt(maxCount-1) == character) counter++;
        return counter == 1;
    }


}
