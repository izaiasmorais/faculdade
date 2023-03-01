#include <stdio.h>
#include <string.h>

int main() {
  char co[] = "abcd";
  int n = strlen(co);
  char cg[n * 9 + 1];
  int i, j, count, k;
  k = 0;

  for (i = 0; i < n; i++) {
    count = 1;

    for (j = i + 1; j < n; j++) {
      if (co[j] != co[i]) {
        break;
      }
      count++;
    }

    cg[k++] = co[i];
    k = k + sprintf(&cg[k], "%d", count);

    i = j - 1;
  }

  printf("%s", cg);

  cg[k] = '\0';

  return 0;
}
