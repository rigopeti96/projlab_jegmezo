package jegmezo;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Nevesített paraméter map, amit a deserializáláshoz használunk
 */
public class DeserializedLine {
    private String name;
    private Map<String, String> parameters;

    /**
     * @return A neve
     */
    public String getName() {
        return name;
    }

    /**
     * @return A paraméter map
     */
    public Map<String, String> getParameters() {
        return parameters;
    }

    /**
     * @param name A neve
     * @param parameters A paramtéter map
     */
    public DeserializedLine(String name, Map<String, String> parameters) {
        this.name = name;
        this.parameters = parameters;
    }

    /**
     * Builder függvény, ami DeserializedLine-ra parseol egy sort
     * @param line Sor amit parseol formátum: enty(param=value,param=value,...)
     * @param entryNames Nevek, amik validak az elején
     * @return Egy DeserializedLine instance
     */
    public static DeserializedLine fromLine(String line, String[] entryNames) {
        Pattern regex = Pattern.compile("([\\-A-Za-z]*)\\(([^\\)]*)\\)");
        Matcher m = regex.matcher(line);
        if (m.find()) {
            String name = m.group(1).trim();
            String paramStr = m.group(2).trim();
            if (!Arrays.asList(entryNames).contains(name)) return null;
            if (m.group(2).contains("(")) return null;

            Map<String, String> paramMap = new HashMap<>();
            String[] parameters = paramStr.length() > 0 ? paramStr.split(",") : new String[0];
            for (String param: parameters) {
                String[] paramSplit = param.trim().split("=");
                if (paramSplit.length != 2) return null;
                paramMap.put(paramSplit[0].trim(), paramSplit[1].trim());
            }
            return new DeserializedLine(name, paramMap);
        } else {
            return null;
        }
    }
}