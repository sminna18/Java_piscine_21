class Program {
    public static void main(String[] args) {

        User nik = new User(0, "Nik", 850);
        User jas = new User(1, "Jas", 210);

        Transaction t_00 = new Transaction(0, nik, jas, Typ.DEBITS, 50);

        System.out.println(nik.getBalance());
        System.out.println(jas.getBalance());

        Transaction t_01 = new Transaction(0, nik, jas, Typ.CREDITS, 50);

        System.out.println(nik.getBalance());
        System.out.println(jas.getBalance());

        Transaction t_02 = new Transaction(0, nik, jas, Typ.CREDITS, 500);

        System.out.println(nik.getBalance());
        System.out.println(jas.getBalance());

        Transaction t_03 = new Transaction(0, nik, jas, Typ.CREDITS, 500);

        System.out.println(nik.getBalance());
        System.out.println(jas.getBalance());
    }
}