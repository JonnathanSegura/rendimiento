public class Ramanujan {

    // ¿Es un número de Ramanujan?
    public static boolean isRamanujan(long n) {
        if (n <= 0) return false;

        long limit = (long) Math.cbrt(n);
        while ((limit + 1) * (limit + 1) * (limit + 1) <= n) {
            limit++;
        }

        int count = 0;
        long a = 1;
        long b = limit;

        while (a < b) {
            long a3 = a * a * a;
            long b3 = b * b * b;
            long sum = a3 + b3;

            if (sum == n) {
                count++;
                if (count == 2) return true;
                a++;
                b--;
            } else if (sum < n) {
                a++;
            } else {
                b--;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        long n = Long.parseLong(args[0]);
        System.out.println(isRamanujan(n) ? "verdadero" : "falso");
    }
}
