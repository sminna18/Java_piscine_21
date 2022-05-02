public class NumberWorker {

    public boolean isPrime (int num) {
        int tmp;

        if (num <= 1)
            throw new IllegalNumberException("Error");
        else if (num == 2) {
            return true;
        }
        else {
            for (int i = 2; i * i <= num + 1; i++) {
                if (num % i == 0)
                    return false;
            }
            return true;
        }
    }

    public int digitsSum(int num) {
        int result = 0;

        if (num < 0)
            num = -num;

        while (num != 0) {
            result += num % 10;
            num /= 10;
        }
        return  result;
    }
}
