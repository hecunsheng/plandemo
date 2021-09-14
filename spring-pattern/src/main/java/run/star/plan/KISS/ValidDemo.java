package run.star.plan.KISS;

import org.apache.commons.lang3.StringUtils;

/**
 * KISS推荐使用第二种
 *
 * YAGNI 不要做过度设计
 * YAGNI(您不需要它) 原则跟 KISS 原则并非一回事儿。
 * KISS 原则讲的是“如何做”的问题（尽量保持简单），而 YAGNI 原则说的是“要不要做”的问题（当前不需要的就不要做）
 * @author hecs
 * @date 2021/7/30 13:48
 */
public class ValidDemo {

    // 第一种实现方式: 使用正则表达式
    public boolean isValidIpAddressV1(String ipAddress) {
        if (StringUtils.isBlank(ipAddress)) return false;
        String regex = "^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\."
                + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
                + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
                + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)$";
        return ipAddress.matches(regex);
    }

    // 第二种实现方式: 使用现成的工具类
    public boolean isValidIpAddressV2(String ipAddress) {
        if (StringUtils.isBlank(ipAddress)) return false;
        String[] ipUnits = StringUtils.split(ipAddress, '.');
        if (ipUnits.length != 4) {
            return false;
        }
        for (int i = 0; i < 4; ++i) {
            int ipUnitIntValue;
            try {
                ipUnitIntValue = Integer.parseInt(ipUnits[i]);
            } catch (NumberFormatException e) {
                return false;
            }
            if (ipUnitIntValue < 0 || ipUnitIntValue > 255) {
                return false;
            }
            if (i == 0 && ipUnitIntValue == 0) {
                return false;
            }
        }
        return true;
    }

    // 第三种实现方式: 不使用任何工具类
    public boolean isValidIpAddressV3(String ipAddress) {
        char[] ipChars = ipAddress.toCharArray();
        int length = ipChars.length;
        int ipUnitIntValue = -1;
        boolean isFirstUnit = true;
        int unitsCount = 0;
        for (int i = 0; i < length; ++i) {
            char c = ipChars[i];
            if (c == '.') {
                if (ipUnitIntValue < 0 || ipUnitIntValue > 255) return false;
                if (isFirstUnit && ipUnitIntValue == 0) return false;
                if (isFirstUnit) isFirstUnit = false;
                ipUnitIntValue = -1;
                unitsCount++;
                continue;
            }
            if (c < '0' || c > '9') {
                return false;
            }
            if (ipUnitIntValue == -1) ipUnitIntValue = 0;
            ipUnitIntValue = ipUnitIntValue * 10 + (c - '0');
        }
        if (ipUnitIntValue < 0 || ipUnitIntValue > 255) return false;
        if (unitsCount != 3) return false;
        return true;
    }
}
