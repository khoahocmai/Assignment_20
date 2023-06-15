package tools;

import java.text.ParseException;

import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class MyUtil {

    // Các hằng mô tả dạng chuỗi ngày tháng năm
    public static final int DMY = 0;
    public static final int MDY = 1;
    public static final int YMD = 2;

    // Hằng mô tả mẫu (regular expression) số phone từ 10 đến 12 ký số
    public static final String Phone_10to12 = "[\\d]{10,12}";
    // Hằng mô tả mẫu cho email thông dụng
    public static final String Email = ".+@.+[.].+";

    public static final Scanner sc = new Scanner(System.in);

    // Tiện ích nhập số nguyên trong khoảng [min,max]
    public static int inputInt(String message, int min, int max) {
        int t = 0;
        if (min > max) {
            t = min;
            min = max;
            max = t;
        }
        boolean OK;
        do {
            OK = true;
            try {
                System.out.print(message + ": ");
                t = Integer.parseInt(sc.nextLine());
                if (t >= min && t <= max) {
                    OK = true;
                } else {
                    System.out.println("The values wrong, Input in min: " + min + " - max: " + max);
                    OK = false;
                }
            } catch (Exception e) {
                OK = false;
                System.out.println("Please input the number");
            }
        } while (!OK);
        return t;
    }

    // Tiện ích nhập số nguyên t >= min
    public static int inputInt(String message, int min) {
        return inputInt(message, min, Integer.MAX_VALUE);
    }

    // Tiện ích nhập số thực (float) trong khoảng [min,max]
    public static float inputFloat(String message, float min, float max) {
        float t = (float) 0.0;
        if (min > max) {
            t = min;
            min = max;
            max = t;
        }
        boolean OK;
        do {
            try {
                System.out.print(message + ": ");
                t = Float.parseFloat(sc.nextLine());
                if (t >= min && t <= max) {
                    OK = true;
                } else {
                    System.out.println("The values wrong, Input in min: " + min + " - max: " + max);
                    OK = false;
                }
            } catch (Exception e) {
                OK = false;
                System.out.println("Please input the number");
            }
        } while (!OK);
        return t;
    }

    // Tiện ích nhập số thực (float) t >= min
    public static float inputFloat(String message, float min) {
        return inputFloat(message, min, Float.MAX_VALUE);
    }

    // Tiện ích nhập số thực (double) trong khoảng [min,max]
    public static double inputDouble(String message, double min, double max) {
        double t = 0.0;
        if (min > max) {
            t = min;
            min = max;
            max = t;
        }
        boolean OK;
        do {
            try {
                System.out.print(message + ": ");
                t = Double.parseDouble(sc.nextLine());
                if (t >= min && t <= max) {
                    OK = true;
                } else {
                    System.out.println("The values wrong, Input in min: " + min + " - max: " + max);
                    OK = false;
                }
            } catch (Exception e) {
                OK = false;
                System.out.println("Please input the number");
            }
        } while (!OK);
        return t;
    }

    // Tiện ích nhập số thực (double) t >= min
    public static double inputDouble(String message, double min) {
        return inputDouble(message, min, Double.MAX_VALUE);
    }

    // Tiện ích nhập chuỗi khác chuỗi trống
    public static String inputNonBlankStr(String message) {
        String S;
        boolean OK = true;
        do {
            System.out.print(message + ": ");
            S = sc.nextLine().toUpperCase().trim();
            if (S == null || S.isEmpty()) {
                OK = false;
            }
        } while (!OK);
        return S;
    }

    // Tiện ích kiểm tra chuỗi nhập có phải chuỗi trống hay không
    public static boolean checkBlankStr(String S) {
        int n = S.length();
        if (n != 0) {
            return false;
        }
        return true;
    }

    // Kiểm tra 1 chuỗi có theo mẫu hay không
    public static boolean validStr(String str, String regex) {
        return str.matches(regex);
    }

    // Tiện ích nhập chuỗi theo một mẫu (email,...)
    // Sử dụng kỹ thuật regular expression - mẫu thông dụng
    public static String inputPattern(String message, String regex, String notification) {
        String S;
        boolean OK;
        do {
            System.out.print(message + ": ");
            S = sc.nextLine().toUpperCase().trim();
            OK = validStr(S, regex);
            if (!OK) {
                System.out.println(notification);
            }
        } while (!OK);
        return S;
    }

    // Tiện ích nhập chuỗi theo mẫu định sẵn
    public static String inputToForm(String message, String form1, String form2) {
        String S;
        boolean OK;
        do {
            System.out.print(message + ": ");
            S = sc.nextLine().toUpperCase().trim();
            OK = (S.equals(form1) || S.equals(form2));
            if (!OK) {
                System.out.println("Input according to the form: " + form1 + " or " + form2);
            }
        } while (!OK);
        return S;
    }

    // Tiện ích nhập boolean 
    public static boolean checkBool(String message) {
        System.out.print(message + " [T/F, Y/N, 1/0]: ");
        String S = sc.nextLine().trim().toUpperCase();
        char c = S.charAt(0);
        return (c == 'T' || c == 'Y' || c == '1');
    }

    // Tiện ích xử lý thông tin ngày tháng năm
    // Kiểm tra năm nhuận
    public static boolean isLeap(int y) {
        boolean result = false;
        if ((y % 400 == 0) || ((y % 4 == 0) && (y % 100 != 0))) {
            result = true;
        }
        return result;
    }

    // Kiểm tra year, month, day có hợp lệ không
    public static boolean checkTime(int y, int m, int d) {
        if (y < 0 || m < 0 || m > 12 || d < 0 || d > 31) {
            return false;
        }
        int maxD = 31;
        if (m == 4 || m == 6 || m == 9 || m == 11) {
            maxD = 30;
        } else if (m == 2) {
            if (isLeap(y)) {
                maxD = 29;
            } else {
                maxD = 28;
            }
        }
        return d <= maxD;
    }

    // Chuyển y, m, d sang kiểu Date
    public static Date toDate(int y, int m, int d) {
        if (!checkTime(y, m, d)) {
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.set(y, m - 1, d);
        return cal.getTime();
    }

    // Chuyển chuỗi ngày tháng thành Date theo format
    public static Date toDate(String dateStr, int dateFormat) {
        int yIndex, mIndex, dIndex;
        switch (dateFormat) {
            case YMD:
                yIndex = 0;
                mIndex = 1;
                dIndex = 2;
                break;
            case MDY:
                yIndex = 2;
                mIndex = 0;
                dIndex = 1;
                break;
            case DMY:
                yIndex = 2;
                mIndex = 1;
                dIndex = 0;
                break;
            default:
                return null;
        }
        // Cắt chuỗi thành 3 phần
        String[] parts = dateStr.split("[/-]");
        // Nếu không dủ 3 phần
        if (parts.length != 3) {
            return null;
        }
        // Đủ 3 phần tách ra y, m, d
        int y = Integer.parseInt(parts[yIndex]);
        int m = Integer.parseInt(parts[mIndex]);
        int d = Integer.parseInt(parts[dIndex]);
        return toDate(y, m, d);
    }

    
    // Các hàm nhập ngày tháng
    // Tiện ích nhập tháng / ngày / năm
    public static Date inputMDY(String message, String notification) {
        Date d;
        String S;
        do {
            System.out.print(message + " [m/d/y]: ");
            S = sc.nextLine().trim();
            d = toDate(S, MDY);
            if (d == null) {
                System.out.println(notification);
            }
        } while (d == null);
        return d;
    }

    // Tiện ích nhập ngày / tháng / năm
    public static Date inputDMY(String message, String notification) {
        Date d;
        String S;
        do {
            System.out.print(message + "[d/m/y]: ");
            S = sc.nextLine().trim();
            d = toDate(S, DMY);
            if (d == null) {
                System.out.println(notification);
            }
        } while (d == null);
        return d;
    }

    // Tiện ích nhập năm / tháng / ngày
    public static Date inputYMD(String message, String notification) {
        Date d;
        String S;
        do {
            System.out.print(message + "[y/m/d]: ");
            S = sc.nextLine().trim();
            d = toDate(S, YMD);
            if (d == null) {
                System.out.println(notification);
            }
        } while (d == null);
        return d;
    }

    // Các hàm chuyễn kiểu ngày tháng năm thành chuỗi 
    // Vì hàm toString chứa quá nhiều dữ liệu, ta chỉ cần ngày tháng năm
    public static String strDMY(Date d) {
        String S = "";
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        return S + c.get(Calendar.DATE) + '/' + (c.get(Calendar.MONTH) + 1) + '/' + c.get(Calendar.YEAR);
    }

    public static String strMDY(Date d) {
        String S = "";
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        return S + (c.get(Calendar.MONTH) + 1) + '/' + c.get(Calendar.DATE) + '/' + c.get(Calendar.YEAR);
    }

    public static String strYMD(Date d) {
        String S = "";
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        return S + c.get(Calendar.YEAR) + '/' + (c.get(Calendar.MONTH) + 1) + '/' + c.get(Calendar.DATE);
    }

    public static int getChoice() {
        String input = "";
        int num = 0;
        boolean OK;
        do {
            OK = true;
            System.out.print("Please enter the option: ");
            input = sc.nextLine();
            OK = validStr(input, "\\d*");
            if (!OK) {
                System.out.println("Invalid!! Input integer number greater than 0");
                OK = false;
            } else if (OK && !input.equals("0")) {
                num = Integer.parseInt(input);
                OK = true;
            } else if (num == 0) {
                System.out.println("Invalid. Input integer number greater than 0");
                OK = false;
            }
        } while (!OK);
        return num;
    }

}
