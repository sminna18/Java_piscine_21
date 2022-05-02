interface UsersList {
    void add_user(User user);
    User retrieve_user_by_id(int id);
    User retrieve_user_by_index(int index);
    int retrieve_user_by_number(int number);

}

class UsersArrayList implements UsersList {
    User mas[] = new User[10];
    int size = 3;
    int count = 0;

    UsersArrayList(){};

    @Override
    public void add_user(User user) {
        if (count == size) {
            int old_size = size;
            size = (int) Math.ceil(size * 1.5);
            User tmp[] =  new User[size];
            for (int i = 0; i < old_size; i++)
            {
                tmp[i] = mas[i];
            }
            mas = tmp;
        }
        mas[count] = user;
        count++;
    }

    @Override
    public User retrieve_user_by_id(int id) {
        for (int i = 0; i < count; i++){
            if (mas[i].getId() == id)
                return mas[i];
        }
        return null;
    }

    @Override
    public User retrieve_user_by_index(int index) {
        return mas[index];
    }

    @Override
    public int retrieve_user_by_number(int number) {
        return count;
    }
}