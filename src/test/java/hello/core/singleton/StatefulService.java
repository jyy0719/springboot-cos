package hello.core.singleton;

public class StatefulService {
   // private int price; // 상태를 유지하는 필드 -> 제거

    // 상태의 값을 수정하는 로직
    public int order(String name, int price) {
        System.out.println("name = " + name + " price = "+ price);
       // this.price = price; //여기가 문제 -> 제거
        return price;
    }

//    public int getPrice(){
//        return price;
//    }
}

