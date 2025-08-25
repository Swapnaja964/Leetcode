import java.util.*;

public class P2 {

    // S-box for substitution step
    static final int[] sbox = {
        0x9, 0x4, 0xA, 0xB,
        0xD, 0x1, 0x8, 0x5,
        0x6, 0x2, 0x0, 0x3,
        0xC, 0xE, 0xF, 0x7
    };

    // GF(2^4) multiplication
    static int multiply(int a, int b) {
        int p = 0;
        for (int i = 0; i < 4; i++) {
            if ((b & 1) != 0) p ^= a;
            boolean carry = (a & 0x8) != 0;
            a <<= 1;
            if (carry) a ^= 0x13; // modulo x⁴ + x + 1
            b >>= 1;
        }
        return p & 0xF;
    }

    // Substitute nibbles using S-box
    static int subNibbles(int input) {
        int result = 0;
        for (int i = 0; i < 4; i++) {
            int nibble = (input >> (i * 4)) & 0xF;
            result |= sbox[nibble] << (i * 4);
        }
        return result;
    }

    // ShiftRows step: swap 2nd and 4th nibble
    static int shiftRows(int input) {
        int n0 = (input >> 0) & 0xF;
        int n1 = (input >> 4) & 0xF;
        int n2 = (input >> 8) & 0xF;
        int n3 = (input >> 12) & 0xF;

        return (n0 << 0) | (n3 << 4) | (n2 << 8) | (n1 << 12);
    }

    // MixColumns for 2x2 nibble matrix
    static int mixColumns(int input) {
        int n0 = (input >> 0) & 0xF;
        int n1 = (input >> 4) & 0xF;
        int n2 = (input >> 8) & 0xF;
        int n3 = (input >> 12) & 0xF;

        int m0 = multiply(1, n0) ^ multiply(4, n1);
        int m1 = multiply(4, n0) ^ multiply(1, n1);
        int m2 = multiply(1, n2) ^ multiply(4, n3);
        int m3 = multiply(4, n2) ^ multiply(1, n3);

        return (m0 << 0) | (m1 << 4) | (m2 << 8) | (m3 << 12);
    }

    // Key expansion for S-AES: generates 3 round keys (K0, K1, K2)
    static int[] keyExpansion(int key) {
        int[] w = new int[6];
        w[0] = (key >> 8) & 0xFF;
        w[1] = key & 0xFF;

        w[2] = w[0] ^ 0b10000000 ^ (sbox[w[1] >> 4] << 4 | sbox[w[1] & 0xF]);
        w[3] = w[2] ^ w[1];

        w[4] = w[2] ^ 0b00110000 ^ (sbox[w[3] >> 4] << 4 | sbox[w[3] & 0xF]);
        w[5] = w[4] ^ w[3];

        int[] roundKeys = new int[3];
        roundKeys[0] = (w[0] << 8) | w[1];
        roundKeys[1] = (w[2] << 8) | w[3];
        roundKeys[2] = (w[4] << 8) | w[5];

        return roundKeys;
    }

    // Full S-AES encryption
    static int encrypt(int plaintext, int key) {
        int[] keys = keyExpansion(key);

        System.out.printf("Round Key 0: %04X\n", keys[0]);
        System.out.printf("Round Key 1: %04X\n", keys[1]);
        System.out.printf("Round Key 2: %04X\n", keys[2]);

        int state = plaintext ^ keys[0];
        System.out.printf("After AddRoundKey (Initial): %04X\n", state);

        state = subNibbles(state);
        System.out.printf("After SubNibbles (Round 1): %04X\n", state);

        state = shiftRows(state);
        System.out.printf("After ShiftRows (Round 1): %04X\n", state);

        state = mixColumns(state);
        System.out.printf("After MixColumns (Round 1): %04X\n", state);

        state ^= keys[1];
        System.out.printf("After AddRoundKey (Round 1): %04X\n", state);

        state = subNibbles(state);
        System.out.printf("After SubNibbles (Round 2): %04X\n", state);

        state = shiftRows(state);
        System.out.printf("After ShiftRows (Round 2): %04X\n", state);

        state ^= keys[2];
        System.out.printf("After AddRoundKey (Round 2 / Final): %04X\n", state);

        return state;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.print("Enter 16-bit plaintext (hex): ");
        int plaintext = Integer.parseInt(in.nextLine(), 16);

        System.out.print("Enter 16-bit key (hex): ");
        int key = Integer.parseInt(in.nextLine(), 16);

        int ciphertext = encrypt(plaintext, key);
        System.out.printf("\nFinal Ciphertext: %04X\n", ciphertext);
    }
}
