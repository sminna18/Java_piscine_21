class Program {
    public static void main(String[] args) {

        User nik = new User( "Nik", 850);
        User jas = new User("Jas", 210);

        UsersArrayList list = new UsersArrayList();
        list.add_user(nik);
        list.add_user(nik);
        list.add_user(nik);
        list.add_user(nik);
        list.add_user(nik);
        list.add_user(nik);
        list.add_user(nik);

//        Transaction t_00 = new Transaction(nik, jas, Typ.DEBITS, 50);
//
//        System.out.println(nik.getBalance());
//        System.out.println(jas.getBalance());
//
//        Transaction t_01 = new Transaction(nik, jas, Typ.CREDITS, 50);
//
//        System.out.println(nik.getBalance());
//        System.out.println(jas.getBalance());
//
//        Transaction t_02 = new Transaction(nik, jas, Typ.CREDITS, 500);
//
//        System.out.println(nik.getBalance());
//        System.out.println(jas.getBalance());
//
//        Transaction t_03 = new Transaction(nik, jas, Typ.CREDITS, 500);
//
//        System.out.println(nik.getBalance());
//        System.out.println(jas.getBalance());

    }
}