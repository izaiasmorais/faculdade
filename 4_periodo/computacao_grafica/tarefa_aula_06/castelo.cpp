#ifdef __APPLE__
#define GL_SILENCE_DEPRECATION
#include <GLUT/glut.h>
#include <OpenGL/gl.h>
#include <OpenGL/glu.h>
#else
#include <GL/glut.h>
#include <GL/gl.h>
#include <GL/glu.h>
#endif

#include <cstdlib>
#include "figuras.h"
#define ESC 27
float R, G, B;

void init(void);
void reshape(int w, int h);
void display(void);

int main(int argc, char** argv) {
	glutInit(&argc, argv);
	glutInitDisplayMode(GLUT_SINGLE | GLUT_RGB);
	glutInitWindowSize(512, 512);
	glutInitWindowPosition(100, 100);
	glutCreateWindow("Tarefa da Aula 06: Castelo 2D");
	init();
	glutReshapeFunc(reshape);
	glutDisplayFunc(display);
	glutMainLoop();
	return EXIT_SUCCESS;
}

void init(void) {
	glClearColor(1.0, 1.0, 1.0, 1.0);
	R = 0.0;
	G = 0.0;
	B = 0.0;
}

void reshape(int w, int h) {
	glMatrixMode(GL_PROJECTION);
	glLoadIdentity();

	glViewport(0, 0, w, h);
	glOrtho(-(w / 2), (w / 2), -(h / 2), (h / 2), -1, 1);

	glMatrixMode(GL_MODELVIEW);
}

void castelo() {
	triangulo();

	glTranslatef(0, 50, 0);
	torre();

	glTranslatef(0, -50, 0);
	parede();
	portao();

	glTranslatef(-200, 0, 0);
	torre();

	glTranslatef(400, 0, 0);
	torre();

	glTranslatef(200, 0, 0);
	triangulo();

	glTranslatef(-200, 50, 0);
	triangulo();
}

void bandeiras() {
	glTranslatef(-200, 325, 0);
	linha();

	glTranslatef(-200, -50, 0);
	linha();

	glTranslatef(400, 0, 0);
	linha();

	glTranslatef(52, 0, 0);
	bandeira();

	glTranslatef(-504, 0, 0);
	bandeira();

	glTranslatef(200, 50, 0);
	bandeira();
}

void janelas() {
	glTranslatef(52, -225, 0);
	glColor3f(0, 0.99, 1);
	circulo(30, 30, true);

	glTranslatef(-200, -50, 0);
	glColor3f(0, 0.99, 1);
	circulo(30, 30, true);

	glTranslatef(400, 0, 0);
	glColor3f(0, 0.99, 1);
	circulo(30, 30, true);
}

void tijolos() {
	glTranslatef(-170, -75, 0);
	tijolo();

	glTranslatef(-115, 0, 0);
	tijolo();

	glTranslatef(-40, -90, 0);
	tijolo();

	glTranslatef(200, 0, 0);
	tijolo();

	glTranslatef(120, 0, 0);
	tijolo_amarelo();

	glTranslatef(0, 80, 0);
	tijolo_amarelo();

	glTranslatef(-440, 0, 0);
	tijolo_amarelo();

	glTranslatef(0, -80, 0);
	tijolo_amarelo();

	glTranslatef(40, 40, 0);
	tijolo_amarelo();

	glTranslatef(360, 0, 0);
	tijolo_amarelo();
}

void fechadura() {
	glTranslatef(-155, -25, 0);
	glColor3f(255, 255, 0);
	circulo(15, 30, true);

	glColor3f(0, 0, 0);
	circulo(12, 30, true);

	glTranslatef(0, 13, 0);
	glColor3f(255, 255, 0);
	circulo(7, 30, true);
}

void display(void) {
	glClear(GL_COLOR_BUFFER_BIT);
	glLoadIdentity();

	castelo();
	bandeiras();
	janelas();
	tijolos();
	fechadura();

	glFlush();
}
