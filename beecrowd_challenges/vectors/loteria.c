#include <stdio.h>
#define tam 6

int main() {
  int A[tam], i, j, c = 0;
  int S[tam];

  for (i = 0; i < tam; i++) {
    scanf("%d", &A[i]);
  }

  for (i = 0; i < tam; i++) {
    scanf("%d", &S[i]);
  }

  for (i = 0; i < tam; i++) {

    for (j = 0; j < tam; j++) {
      if (A[i] == S[j]) {
        c++;
      }
    }
  }

  if (c == 3) {
    printf("terno\n");
  } else if (c == 4) {
    printf("quadra\n");
  } else if (c == 5) {
    printf("quina\n");
  } else if (c == 6) {
    printf("sena\n");
  } else {
    printf("azar\n");
  }

  return 0;
}
