import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.*;
/*The objective of this portfolio assignment is to teach you a practical
application of the number theory we are learning in class that is ubiquitous
and of fundamental importance to security in the internet age. RSA is called
an asymmetric cryptographic algorithm because it requires two keys, and only
one of the two keys is private. Throughout this assignment you will learn:

(1.) How the public-key cryptography algorithm works.
(2.) What is the point of having a private key and a public key?
(3.) How does one create the public key?
(4.) How does one create the private key?
(5.) How does one "sign" a document?
(6.) How to code the algorithm to encrypt a message, send it to someone
and then have them decrypt the message.

What follows below is "skeleton code" from a working program. Your job will be
to use what you have learned in class to complete the missing segments of the
code.

Anywhere you see a comment which is a full line of #'s, that means you are
expected to add your own code to those areas.
*/

public class RSA {

    public static void main(String[] args) {
		/*
		 * Part 1 - prompt user for a string input of ASCII characters. Encode
		 * the string into a list of integers.
		 *
		 * This section has been completed for you.
		 */
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Enter the message you would like to encode, using any ASCII characters: ");
        String input = keyboard.nextLine();
        int[] ASCIIvalues = new int[input.length()];
        for (int i = 0; i < input.length(); i++) {
            ASCIIvalues[i] = input.charAt(i);
        }
        String ASCIInumbers = "";
        for (int j = 0; j < ASCIIvalues.length; j++) {
            ASCIInumbers += ASCIIvalues[j] + " ";
        }
        System.out.println("-----------------------------------------");
        System.out.println();
        System.out.println("The ASCII coded sequence is:");
        System.out.println();
        System.out.println(ASCIInumbers);
        System.out.println();
        System.out.println("-----------------------------------------");

        long P = bigPrime();
        long Q = P;
        while (Q == P) {
            Q = bigPrime();
        }
        // Print the results here.
        System.out.println();
        System.out.println("The two primes are P = " + P + " and Q = " + Q);
        System.out.println();
        System.out.println("The product of the two primes, P*Q, is the modulus for both the private");
        System.out.println("and public key and is thus part of the public domain: " + P * Q);
        System.out.println("Something interesting to note about how this algorithm works is that");
        System.out.println("while P*Q is public, factoring very large numbers is very difficult");
        System.out.println("computationally, so only the person with the knownledge of the");
        System.out.println("individual values P and Q, has the tools to derive the private key.");
        System.out.println();
        System.out.println("-----------------------------------------");

		/*
		 * Part 3 - Next, we calculate the public key exponent, called E. E is
		 * chosen to be an integer which is relatively prime to another integer
		 * called the totient, usually represented by phi. In this step,
		 * calculate phi, which is equal to (P-1)(Q-1). The create a public key
		 * exponent, create an integer E that is relatively prime to phi, and
		 * also less than phi.
		 *
		 * THIS SECTION HAS NOT BEEN COMPLETED FOR YOU.
		 */

        // calculate phi here
        ////////////////////////////////////////////////

        // write an algorithm to find E which is relatively prime to phi here.
        // in other words find E such that gcd(phi,E)=1, and E<phi.

        ///////////////////////////////////////////////

        System.out.println();
        System.out.println("The totient is phi = " + phi);
        System.out.println("and");
        System.out.println("The public key is E = " + E);
        System.out.println();
        System.out.println("-----------------------------------------");

		/*
		 * Part 4 - Find the multiplicative inverse of E mod (P-1)(Q-1). In this
		 * step, we are looking for D such that E*D = 1 mod phi. Remember, phi
		 * is called the totient and is equal to (P-1)(Q-1). To do this, we need
		 * to find what are called the Bezout coefficients of E and phi. The
		 * algorithm to get these numbers is called the 'extended euclidean
		 * algorithm.' In part 4, you used the gcd function from a built-in
		 * library to make sure that the GCD of the integer you generated and
		 * phi is 1. [ie gcd(phi,E) = 1 ]. Now, because we know gcd(phi,E)=1,
		 * then we proved in class that there MUST exist coefficients x and y
		 * such that x*phi + y*E = 1. These coefficients are called the Bezout
		 * coefficients, and the extended euclidean algorithm we practiced in
		 * class finds them. The very important thingto notice about the Bezout
		 * coefficient y is that it must also be the multiplicative inverse of E
		 * (mod phi). Note: if x*phi + y*E = 1, then y*E = 1 - x*phi, which
		 * means y*E = 1 mod phi. In this next section, you should write the
		 * first part of the extended Euclidean algorithm, which will confirm
		 * that gcd(E,phi)=1, and will also generate a list of remainders and
		 * quotients which will be needed to perform the second part of the EA
		 * (see pdf on collab). Next, write the second part of the extended
		 * euclidean algorithm, which returns the bezout coefficients to find
		 * the multiplicative inverse of E (mod phi). We will call that inverse
		 * D, and D is called the PRIVATE KEY EXPONENT, and should only be known
		 * by the person to whom the message is being sent.
		 *
		 * THIS SECTION HAS NOT BEEN COMPLETED FOR YOU.
		 */

        //Write your code for the Euclidean Algorithm Here.

        //////////////////////////////////////////////

        //Next, begin the second part of the euclidean algorithm, where you substitute
//your way back up the algorithm to find the multiplicative inverse of E.

        ////////////////////////////////////////////////

        //Display results


        System.out.println("The Bezout Equation for E and phi is given by:");
        System.out.println(y + "*" + E + "+" + x + "*" + phi + " = " + (y * E + x * phi));

        //depending on whether you take an odd or even number of steps, D may end up
//as a negative. Make sure D is positive so that you may use it as an exponent..

//Display results for of extended Euclidean Algorithm


        System.out.println("Which means the private key is D = " + D);
        System.out.println("D*E mod phi = " + (D * E) % phi);
        System.out.println("So the above equation is written as");
        System.out.println(D + " * " + E + " mod " + phi + " = " + (D * E) % phi);
        System.out.println();
        System.out.println("-----------------------------------------");

		/*
		 * Part 5 - Encrypt the ASCII-coded message using the public key. This
		 * is accomplished by taking the ASCII-coded list and raising each
		 * component to power of the public key exponent, and dividing modulus
		 * PQ. One hint to note: taking the ASCII code and raising to the power
		 * E leads to a HUGE number, which will cause an overflow. We must
		 * instead make use of the fact that we can divide modulo n after each
		 * multiplication. (ie if we take a^b mod n, where a and b are large, we
		 * would instead take a*a mod n = c, then c*a mod n = d, then d*a mod
		 * n....and so on, until we have performed the multiplication b times.)
		 * THIS SECTION IS NOT COMPLETED FOR YOU.
		 */

        //use stringEncrypted as the name of your variable for the list of encrypted
//integers. Perform the calculation to encrypt ASCIIstringEncoded here.


        System.out.println();
        System.out.println("The ASCII sequence after encrypting with the public key is:");
        System.out.println();
        System.out.println(stringEncrypted);
        System.out.println();
        System.out.println("-----------------------------------------");

		/*
		 * Part 6 - To decrypt the encrypted code, one raises each of the
		 * elements in the encrypted string to the private key exponent, and
		 * dividing modulus PQ.
		 *
		 * THIS SECTION IS NOT COMPLETED FOR YOU.
		 */

        //use the variable name stringDecrypted for the list of decrypted integers.
//Perform the calculation to decrypt the list stringEncrypted into the list
//stringDecrypted here.

        /////////////////////////////////////////////////////
        System.out.println();
        System.out.println("The ASCII sequence after decrypting with the private key is:");
        System.out.println();
        System.out.println(stringDecrypted);
        System.out.println();
        System.out.println("-----------------------------------------");

		/*
		 * Part 7 - The decrypted message should now be a list of integers which
		 * are the ASCII code of the of the original message. In other words,
		 * your decrypted list should be the exact same list as the
		 * ASCIIstringEncoded list (except you will notice they are now long
		 * format, because the integers were quite large at one point).
		 *
		 * This section has been completed for you.
		 */

        String message = "";
        for (int n = 0; n < stringDecrypted.length; n++) {
            message = message + (char) stringDecrypted[n];
        }

        System.out.println();
        System.out.println("The decrypted message is:");
        System.out.println();
        System.out.println(message);
        System.out.println();
        System.out.println("-----------------------------------------");

		/*Part 8 - To recap what we have learned, let's consider what has happened here.
The receiver of the message has a private key. The key was derived from a
knowledge of P and Q. The public has knowledge only of the number P*Q (which is
large and difficult to factor), and E, which is the public key. If someone
wants to send a *SECRET* message to the receiver, they use the public key to
encrypt that message. The only (known) way to decrypt is with the private key.
Another cool thing about the public/private keys, is that it allows the
receiver to use his/her private key to digitally "sign" a message. This is
necessary because your public key is known to anyone, which means anyone can
send you a message. If you want a way to securely verify from whom the
message was delivered, you can also use RSA encryption have that person "sign"
the message as well. THIS SECTION HAS NOT BEEN COMPLETED FOR YOU.*/
        //To digitally sign your message, input a 4 digit PIN...

        System.out.println("Enter a 4 digit integer 'PIN' : ");
        int pin = keyboard.nextInt();

        //Now, to verify you are the sender, you will encrypt your pin with your private
//key (D, which no one else knows)...

//Here, you should write code which encrypts the pin number with the private key
//Use the variable PINencrypted for the integer value.

/////////////////////////////////////////////////////////

        System.out.println();
        System.out.println("The encrypted PIN is: " + pinEncrypted);
        System.out.println();

        //Then you can tell the receiver, "my PIN is 1234, do not accept any message
//from someone whose PIN does not decrypt to 1234." Then, you simply attach
//your encrypted PIN at the end of the message you send. To decrypt, the
//receiver uses the public key, which everyone knows. If the decrypted PIN
//matches 1234, then the receiver knows the mssage came from you.

//Here you should write your code for decrypting the int PINencrypted. Use the
//variable PINdecrypted

/////////////////////////////////////////////////////

        System.out.println();
        System.out.println("The decrypted PIN is: " + pinDecrypted);
        System.out.println();


    }

	/*
	 * Part 2 - Define two very large, random, primes. Do not allow the primes
	 * to be larger than 2*10^3 or so, as larger numbers may cause an overflow
	 * in python. On the other hand, be sure that the primes are at least as
	 * large as 5*10^2. Interestingly, real encryption will be done with primes
	 * that are 1024 bits, which are numbers that about 300 (i.e. greater than
	 * 10^300) digits long. Call the two primes P and Q. Their product, PQ will
	 * be the modulus for both the public and private keys. This number, then,
	 * is public information. The individual primes, P and Q, are private.
	 *
	 * This section has been completed for you.
	 */

    public static int bigPrime() {
        boolean prime = false;
        int n = 0;
        while (!prime) {
            Random rand = new Random();
            n = rand.nextInt(500);
            n = 2 * (n + 500) + 1;
            int sqrtn = (int) Math.pow(n, 0.5) + 1;
            for (int i = 3; i < sqrtn; i += 2) {
                if (n % i == 0) {
                    prime = false;
                    break;
                } else {
                    prime = true;
                }
            }
        }
        return n;
    }

    // Method to compute the GCD of two positive integers.
    public static long gcd(long a, long b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }

}