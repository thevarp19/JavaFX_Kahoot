package kahoot.it.project2.tasks;

public class GenericTask {
    public static void main(String[] args) {
        Integer[] backpack= new Integer[30];
        for(int i=0;i<backpack.length;i++){
            backpack[i]=(int)(Math.random()*100);
        }
        System.out.println(linearSearch(backpack, 2));
        System.out.println(linearSearch(backpack, 9));
        System.out.println(linearSearch(backpack, 56));
        System.out.println(linearSearch(backpack, 33));
        System.out.println(linearSearch(backpack, 19));
    }
    public static <E extends Comparable<E>> int linearSearch(E[] list,E key){
       for(int i=0;i< list.length;i++){
           if(list[i].compareTo(key)==0){
               return i;
           }
       }
       return -1;
    }
}
