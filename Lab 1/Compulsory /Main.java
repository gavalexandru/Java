/**
 * Autor: Alexandru Gavril
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World");
        String[] languages = {"C", "C++", "C#", "Python", "Go", "Rust", "JavaScript", "PHP", "Swift", "Java"};
        int n = (int)(Math.random() * 1_000_000);
        n = n*3;
        n = n+Integer.parseInt("10101",2);
        n = n+Integer.parseInt("FF",16);
        n = n*6;
        int ok=0;
        while(ok!=1){
            if(n<10) ok=1;
            else {
            int aux=0;
            while(n!=0){
                aux+=n%10;
                n=n/10;
            }
            n=aux;
            }
        }
        System.out.println("Willy-nilly, this semester I will learn " + languages[n]);
    }
}
