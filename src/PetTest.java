public class PetTest
{
    public static void main (String [] args)
    {
        Pet [] myPets = new Pet [3];
        myPets [0] = new Dog ("Boo-Boo", 2008);
        myPets [1] = new Cat ("Tigi", 2016);
        myPets [2] = new Cat ("Sheldon", 2011);
        
        for (Pet p : myPets)
        {
            System.out.println (p.getName() + " says " + p.speak() );
        }
    }
}
