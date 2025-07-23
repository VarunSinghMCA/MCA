#include <stdio.h>

int main() {
    int n = 3412, n1 = n;
    int digit, r = 0;
    while(n!=0){
        digit = n % 10;
        r = r * 10 + digit;
        n = n / 10;
    }
    printf("Reverse of %d is %d\n", n1, r);

    return 0;
}