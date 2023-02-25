#include <stdio.h>
#include <string.h>

int main() {
  int n;
  char ra[101];
  scanf("%d", &n);

  for (int i = 0; i < n; i++) {
    scanf("%s", ra);

    if (strlen(ra) != 20 || strncmp(ra, "RA", 2) != 0) {
      printf("INVALID DATA\n");
      continue;
    }

    char id[19];
    strncpy(id, ra + 2, 18);
    id[18] = '\0';

    int j = 0;
    while (id[j] == '0' && j < 18) {
      j++;
    }
    if (j == 18) {
      printf("INVALID DATA\n");
      continue;
    }

    printf("%s\n", id + j);
  }

  return 0;
}
