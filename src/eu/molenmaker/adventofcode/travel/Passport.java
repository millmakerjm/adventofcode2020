package eu.molenmaker.adventofcode.travel;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
byr (Birth Year)
iyr (Issue Year)
eyr (Expiration Year)
hgt (Height)
hcl (Hair Color)
ecl (Eye Color)
pid (Passport ID)
cid (Country ID)
 */
public class Passport {

    String birthYear;
    String issueYear;
    String expirationYear;
    String height;
    String hairColor;
    String eyeColor;
    String passwordID;
    String countryID;

    Pattern pattern = Pattern.compile("([a-z]+):([\\S]+)");
    Pattern hexPattern = Pattern.compile("^#([a-f0-9]{6})");
    private static final List<String> validEyeColors = Arrays.asList(new String[]{"amb", "blu", "brn", "gry", "grn", "hzl", "oth"});

    /*
    byr (Birth Year) - four digits; at least 1920 and at most 2002.
    iyr (Issue Year) - four digits; at least 2010 and at most 2020.
    eyr (Expiration Year) - four digits; at least 2020 and at most 2030.
    hgt (Height) - a number followed by either cm or in:
    If cm, the number must be at least 150 and at most 193.
    If in, the number must be at least 59 and at most 76.
    hcl (Hair Color) - a # followed by exactly six characters 0-9 or a-f.
    ecl (Eye Color) - exactly one of: amb blu brn gry grn hzl oth.
    pid (Passport ID) - a nine-digit number, including leading zeroes.
    cid (Country ID) - ignored, missing or not.
     */
    public void parseLine(String line) {
        Matcher matcher = pattern.matcher(line);
        while (matcher.find()) {
            String value = matcher.group(2);

            switch (matcher.group(1)) {
                case "byr":
                    if (isBetween(value, 1920, 2002)) {
                        birthYear = value;
                    }
                    break;
                case "iyr":
                    if (isBetween(value, 2010, 2020)) {
                        issueYear = value;
                    }
                    break;
                case "eyr":
                    if (isBetween(value, 2020, 2030)) {
                        expirationYear = value;
                    }
                    break;
                case "hgt":
                    if (isValidHeight(value)) {
                        height = value;
                    }
                    break;
                case "hcl":
                    if (hexPattern.matcher(value).matches()) {
                        hairColor = value;
                    }
                    break;
                case "ecl":
                    if (validEyeColors.contains(value)) {
                        eyeColor = value;
                    }
                    break;
                case "pid":
                    if (StringUtils.length(value) == 9 && StringUtils.isNumeric(value)) {
                        passwordID = value;
                    }
                    break;
                case "cid":
                    countryID = value;
                    break;
            }
        }

    }

    private boolean isBetween(String value, long min, long max) {
        if (StringUtils.isNumeric(value)) {
            long nr = Long.parseLong(value);
            return nr >= min && nr <= max;
        }
        return false;
    }

    private boolean isValidHeight(String value) {
        if (StringUtils.endsWith(value, "cm")) {
            return isBetween(StringUtils.left(value, StringUtils.length(value) - 2), 150, 193);
        } else if (StringUtils.endsWith(value, "in")) {
            return isBetween(StringUtils.left(value, StringUtils.length(value) - 2), 59, 76);
        }
        return false;
    }

    public boolean isValid() {
        return (   birthYear != null &&
                issueYear != null &&
                expirationYear != null &&
                height != null &&
                hairColor != null &&
                eyeColor != null &&
                passwordID != null);
    }

    @Override
    public String toString() {
        return "Passport{" +
                "birthYear='" + birthYear + '\'' +
                ", issueYear='" + issueYear + '\'' +
                ", expirationYear='" + expirationYear + '\'' +
                ", height='" + height + '\'' +
                ", hairColor='" + hairColor + '\'' +
                ", eyeColor='" + eyeColor + '\'' +
                ", passwordID='" + passwordID + '\'' +
                ", countryID='" + countryID + '\'' +
                '}';
    }
}
