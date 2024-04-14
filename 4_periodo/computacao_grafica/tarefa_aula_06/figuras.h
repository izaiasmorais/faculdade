#ifndef circulo_h
#define circulo_h
#include <cmath>
#ifndef M_PI
#define M_PI 3.14159265f
#endif

void parede() {
	glColor3f(255, 255, 0);
	glBegin(GL_POLYGON);
	glVertex2f(150, 100);
	glVertex2f(-150, 100);
	glVertex2f(-150, -100);
	glVertex2f(150, -100);
	glEnd();
}

void portao() {
	glColor3f(0, 0, 0);
	glBegin(GL_POLYGON);
	glVertex2f(-50, 0);
	glVertex2f(50, 0);
	glVertex2f(50, -100);
	glVertex2f(-50, -100);
	glEnd();
}

void torre() {
	glColor3f(1.0, 0.55, 0.0);
	glBegin(GL_POLYGON);
	glVertex2f(-50, 150);
	glVertex2f(50, 150);
	glVertex2f(50, -100);
	glVertex2f(-50, -100);
	glEnd();
}

void triangulo() {
	glColor3f(1.0, 0, 0);
	glBegin(GL_TRIANGLES);
	glVertex2f(-100, 150);
	glVertex2f(-200, 225);
	glVertex2f(-300, 150);
	glEnd();
}

void linha() {
	glColor3f(0, 0, 0);
	glBegin(GL_POLYGON);
	glVertex2f(-2, 0);
	glVertex2f(2, 0);
	glVertex2f(2, -100);
	glVertex2f(-2, -100);
	glEnd();
}

void bandeira() {
	glColor3f(0, 0.99, 0);
	glBegin(GL_POLYGON);
	glVertex2f(-50, 0);
	glVertex2f(50, 0);
	glVertex2f(50, -50);
	glVertex2f(-50, -50);
	glEnd();
}

void tijolo() {
	glColor3f(1.0, 0.55, 0.0);
	glBegin(GL_POLYGON);
	glVertex2f(50, 20);
	glVertex2f(0, 20);
	glVertex2f(0, 0);
	glVertex2f(50, 0);
	glEnd();
}

void tijolo_amarelo() {
	glColor3f(255, 255, 0);
	glBegin(GL_POLYGON);
	glVertex2f(50, 20);
	glVertex2f(0, 20);
	glVertex2f(0, 0);
	glVertex2f(50, 0);
	glEnd();
}

void circulo(float raio, int num_linhas, bool preenchido) {
	int i;
	GLfloat angulo;

	angulo = (2 * M_PI) / num_linhas;

	if (preenchido) glBegin(GL_TRIANGLE_FAN);
	else glBegin(GL_LINE_LOOP);

	for (i = 1; i <= num_linhas; i++) {
		glVertex2f(-raio * sin(i * angulo), raio * cos(i * angulo));
	}

	glEnd();
}

#endif
