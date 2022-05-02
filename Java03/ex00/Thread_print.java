class Thread_print extends Thread{

    int count;
    String word;

    Thread_print(int count, String word) {
        this.count = count;
        this.word = word;
    }

    @Override
    public void run () {
        for (int i = 0; i < count; i++)
            System.out.println(word);
    }

}