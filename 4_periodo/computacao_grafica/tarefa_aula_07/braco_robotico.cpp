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
#include <stdio.h>
#include <stdlib.h>
#define ESC 27
#define PI 3.14159265

static int ombro = 0, cotovelo = 0, punho = 0, indicador = 0, polegar = 0, anelar = 0, rotacao = 0;

void init(void);
void keyboard(unsigned char key, int x, int y);
void specialKeys(int key, int x, int y);
void display(void);
void reshape(int w, int h);

int main(int argc, char** argv) {
	glutInit(&argc, argv);

	glutInitDisplayMode(GLUT_RGB | GLUT_DOUBLE | GLUT_DEPTH);
	glutInitWindowSize(800, 600);
	glutInitWindowPosition(100, 100);
	glutCreateWindow("Computacao Grafica: Braco Robotico");

	init();

	glutDisplayFunc(display);
	glutReshapeFunc(reshape);
	glutKeyboardFunc(keyboard);
	glutSpecialFunc(specialKeys);
	glutMainLoop();

	return EXIT_SUCCESS;
}

void init(void) {
	glClearColor(1.0, 1.0, 1.0, 1.0);
	glEnable(GL_DEPTH_TEST);
}

void reshape(int w, int h) {
	glMatrixMode(GL_PROJECTION);
	glLoadIdentity();

	glViewport(0, 0, (GLsizei)w, (GLsizei)h);
	gluPerspective(60, (float)w / (float)h, 1.0, 9.0);
	gluLookAt(0.0, 0.0, 7.0,
		0.0, 1.0, 0.0,
		0.0, 1.0, 0.0);
	glMatrixMode(GL_MODELVIEW);
}

void keyboard(unsigned char key, int x, int y) {
	switch (key) {
	case 'o': ombro = (ombro - 5) % 360; break; // sentido horario
	case 'O': ombro = (ombro + 5) % 360; break; // sentido anti-horario
	case 'c': cotovelo = (cotovelo - 5) % 360; break; // sentido horario
	case 'C': cotovelo = (cotovelo + 5) % 360; break; // sentido anti-horario
	case 'm': punho = (punho - 5) % 360; break; // sentido horario
	case 'M': punho = (punho + 5) % 360; break; // sentido anti-horario
	case 'i': indicador = (indicador - 5) % 360; break; // sentido horario
	case 'I': indicador = (indicador + 5) % 360; break; // sentido anti-horario
	case 'p': polegar = (polegar - 5) % 360; break; // sentido horario
	case 'P': polegar = (polegar + 5) % 360; break; // sentido anti-horario
	case 'a': anelar = (anelar - 5) % 360; break; // sentido horario
	case 'A': anelar = (anelar + 5) % 360; break; // sentido anti-horario
	case 'y': rotacao = (rotacao - 5) % 360; break; // sentido horario
	case 'Y': rotacao = (rotacao + 5) % 360; break; // sentido anti-horario
	case ESC: exit(EXIT_SUCCESS); break; // sai do programa
	}
	glutPostRedisplay();
}

void specialKeys(int key, int x, int y) {
	switch (key) {
	case GLUT_KEY_UP: rotacao = (rotacao - 5) % 360; break;
	case GLUT_KEY_DOWN: rotacao = (rotacao + 5) % 360; break;
	}
	glutPostRedisplay();
}

void display(void) {
	glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); //Limpa o Buffer de Cores
	glLoadIdentity();

	// Rotação do braço em torno do eixo Y
	glRotatef((GLfloat)rotacao, 0.0, 1.0, 0.0);

	// Base
	glPushMatrix();
	glTranslatef(0.0, -2.0, 0.0);
	glRotatef(-90, 1, 0, 0);
	glColor3f(0.0, 0.0, 0.0); // Preto metálico
	glutSolidCone(1.0, 1.0, 30, 30); // base, altura, fatias, pilhas
	glPopMatrix();

	// Ombro
	glPushMatrix();
	glTranslatef(0.0, -1.0, 0.0);
	glColor3f(0.75, 0.75, 0.75); // Prata
	glutSolidSphere(0.5, 30, 30); // raio, fatias, pilhas
	glPopMatrix();

	//Movimento do braço
	glPushMatrix();
	// origem posicionada no ombro
	glTranslatef(0.0, -1.0, 0.0);
	glRotatef((GLfloat)ombro, 0.0, 0.0, 1.0);

	// origem posicionada no centro do braço
	glTranslatef(0.0, 1.0, 0.0);
	// Braco
	glPushMatrix();
	glRotatef(90, 0, 0, 1);
	glScalef(2.0, 0.5, 0.5);
	glColor3f(0.25, 0.25, 0.25); // Cinza metálico
	glutSolidCube(1.0); // Tamanho
	glPopMatrix();

	// Cotovelo
	glPushMatrix();
	glTranslatef(0.0, 1.0, 0.0);
	glColor3f(0.75, 0.75, 0.75); // Prata
	glutSolidSphere(0.4, 30, 30); // raio, fatias, pilhas
	glPopMatrix();

	// Movimento do antebraco
	glPushMatrix();
	//origem posicionada no cotovelo
	glTranslatef(0.0, 1.0, 0.0);
	glRotatef((GLfloat)cotovelo, 0.0, 0.0, 1.0);
	glTranslatef(0.0, 1.0, 0.0);
	// Antebraco
	glPushMatrix();
	glRotatef(90, 0, 0, 1);
	glScalef(2.0, 0.4, 0.4);
	glColor3f(0.25, 0.25, 0.25); // Cinza metálico
	glutSolidCube(1.0); // Tamanho
	glPopMatrix();

	// Mão
	glPushMatrix();
	glTranslatef(0.0, 0.75, 0.0);
	glRotatef((GLfloat)punho, 0.0, 0.0, 1.0);
	// Origem posicionada no centro da mão
	glTranslatef(0.0, 0.5, 0.0);

	// Base da mão
	glPushMatrix();
	glScalef(1.5, 0.5, 0.5);
	glColor3f(0.5, 0.25, 0.0); // Laranja escuro metálico
	glutSolidCube(1.0);
	glPopMatrix();

	// Dedo Indicador
	glPushMatrix();
	glTranslatef(-0.7, 0.3, 0.0);
	glRotatef((GLfloat)indicador, 1.0, 0.0, 0.0);
	glTranslatef(0.0, 0.3, 0.0);
	glPushMatrix();
	glScalef(0.2, 0.6, 0.2);
	glColor3f(0.0, 0.0, 0.0); // Preto metálico
	glutSolidCube(1.0);
	glPopMatrix();
	glPopMatrix();

	// Dedo Polegar
	glPushMatrix();
	glTranslatef(0.7, 0.3, 0.0);
	glRotatef((GLfloat)polegar, 1.0, 0.0, 0.0);
	glTranslatef(0.0, 0.3, 0.0);
	glPushMatrix();
	glScalef(0.2, 0.6, 0.2);
	glColor3f(0.0, 0.0, 0.0); // Preto metálico
	glutSolidCube(1.0);
	glPopMatrix();
	glPopMatrix();

	// Dedo Anelar
	glPushMatrix();
	glTranslatef(0.0, 0.3, -0.7);
	glRotatef((GLfloat)anelar, 0.0, 0.0, 1.0);
	glTranslatef(0.0, 0.3, 0.0);
	glPushMatrix();
	glScalef(0.2, 0.6, 0.2);
	glColor3f(0.0, 0.0, 0.0); // Preto metálico
	glutSolidCube(1.0);
	glPopMatrix();
	glPopMatrix();

	glPopMatrix(); // Fim da transformação da mão
	glPopMatrix(); // Fim da transformação do antebraco
	glPopMatrix(); // Fim da transformação do braço

	// Troca os buffers, mostrando o que acabou de ser desenhado
	glutSwapBuffers();
}
