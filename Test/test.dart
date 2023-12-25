void main() {
  print("hey");
}

class User {
  String us = "";
  int age = 0;
  User(String us, int age) {
    this.age = age;
    this.us = us;
  }
  void pp() {
    print("hello");
  }
}

class child extends User {
  String ch = "";
  child(String us, int age, String ch) : super(us, age);

  xx() {
    print("Hi");
  }
}
