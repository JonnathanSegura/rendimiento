public class Inversions {

    // Cuenta el número de inversiones sin mutar el arreglo original
    public static long count(int[] a) {
        int[] copy = new int[a.length];
        System.arraycopy(a, 0, copy, 0, a.length);
        int[] aux = new int[a.length];
        return mergeSort(copy, aux, 0, a.length - 1);
    }

    private static long mergeSort(int[] a, int[] aux, int lo, int hi) {
        if (hi <= lo) return 0;
        int mid = lo + (hi - lo) / 2;
        long count = 0;
        count += mergeSort(a, aux, lo, mid);
        count += mergeSort(a, aux, mid + 1, hi);
        count += merge(a, aux, lo, mid, hi);
        return count;
    }

    private static long merge(int[] a, int[] aux, int lo, int mid, int hi) {
        for (int k = lo; k <= hi; k++) aux[k] = a[k];
        long count = 0;
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) a[k] = aux[j++];
            else if (j > hi) a[k] = aux[i++];
            else if (aux[j] < aux[i]) {
                a[k] = aux[j++];
                count += (mid - i + 1);
            } else a[k] = aux[i++];
        }
        return count;
    }

    // Genera una permutación de 0..n-1 con exactamente k inversiones
    public static int[] generate(int n, long k) {
        long maxInv = (long) n * (n - 1) / 2;
        if (k == 0) {
            int[] inc = new int[n];
            for (int i = 0; i < n; i++) inc[i] = i;
            return inc;
        }
        if (k == maxInv) {
            int[] dec = new int[n];
            for (int i = 0; i < n; i++) dec[i] = n - 1 - i;
            return dec;
        }

        int[] code = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            int maxHere = n - 1 - i;
            int use = (int) Math.min(k, maxHere);
            code[i] = use;
            k -= use;
        }

        int[] nums = new int[n];
        for (int i = 0; i < n; i++) nums[i] = i;

        int[] perm = new int[n];
        int alive = n;
        for (int i = 0; i < n; i++) {
            int idx = code[i];
            perm[i] = nums[idx];
            for (int j = idx + 1; j < alive; j++) {
                nums[j - 1] = nums[j];
            }
            alive--;
        }
        return perm;
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        long k = Long.parseLong(args[1]);
        int[] permutation = generate(n, k);
        for (int i = 0; i < permutation.length; i++) {
            if (i > 0) System.out.print(" ");
            System.out.print(permutation[i]);
        }
        System.out.println();
    }
}