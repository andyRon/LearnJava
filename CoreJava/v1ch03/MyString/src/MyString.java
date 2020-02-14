public class MyString {
    public static void main(String[] args) {
        String str = "\uD83E\uDD29Hello";
        System.out.println(str.charAt(1));
        System.out.println(str.codePointAt(2));

        var builder = new StringBuffer();

        System.out.println("java\u2122");


    }


}


