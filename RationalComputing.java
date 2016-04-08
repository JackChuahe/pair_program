package com.jackchua.dijkstra;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * @author JackChua
 * @brief This is a program to automatically generate expressions
 * */
public class RationalComputing {

	private static int NUMS = 5; // ���ʽ������Ĭ��ֵ
	private static int MAX_VALUE = 100; // ���ʽ����ֵ��Χ��Ĭ��ֵ ���� 100 ����
	/** <Const number */

	private static List<Rational> listArr = new ArrayList<Rational>();
	/** <List To store Rational objects */
	private static final String[] OPREATORS = { "+", "-", "*", "/" };
	/** <a constant array to store operators */
	private static Scanner scanner = new Scanner(System.in);

	/** <a scanner to get data from user */

	/**
	 * @brief This is the main function of this program
	 * @param [in] args
	 */
	public static void main(String[] args) {

		getInitMaxValueAndExpNum(); // �û�������ȷ�����ɼ����ڵı��ʽ���Լ����ʽ�ĸ���

		int i = NUMS;
		listArr.clear();

		Random random = new Random(System.currentTimeMillis());
		while ((--i) >= 0) {
			int opA = random.nextInt(MAX_VALUE + 1);
			int opB = random.nextInt(MAX_VALUE + 1);
			String com = OPREATORS[random.nextInt(4)];
			boolean isWrong = true;
			// �ж��Ƿ�����и���ֵ���� �����������������
			while (isWrong) {
				if (com == "/" && (opB == 0 || (opA % opB) != 0)) { // ��������Ϊ0
																	// �����ܱ�����
					opB = random.nextInt(MAX_VALUE + 1);
				} else if (com == "-" && opA < opB) { // ���������и���
					opA = random.nextInt(MAX_VALUE + 1);
					opB = random.nextInt(MAX_VALUE + 1);
				} else if (com == "+" && (opA + opB) > MAX_VALUE) { // �����ֵ���ܴ���MAX_VALUE
					opA = random.nextInt(MAX_VALUE + 1);
					opB = random.nextInt(MAX_VALUE + 1);
				} else if (com == "*" && (opA * opB) > MAX_VALUE) { // �����ֵ���ܴ���
																	// MAX_VALUE
					opA = random.nextInt(MAX_VALUE + 1);
					opB = random.nextInt(MAX_VALUE + 1);
				} else {
					break;
				}
			}
			Rational tempRa = new Rational(opA, opB, com);
			listArr.add(tempRa);
		}
		//
		ouput(); // ���
		input(); // �û�������
		outputResult();// ����û��Ƿ�������ȷ
	}

	/**
	 * @brief to determine the MAX_VLAUE of the expressions
	 * @return void
	 */
	public static void getInitMaxValueAndExpNum() {

		// ������ʽ�������ֵ��Χ
		while (true) {
			System.out.println("������һ������0 С�ڵ���10000����������ȷ�����ʽ����ֵ��Χ:");
			if (scanner.hasNextInt()) {
				MAX_VALUE = scanner.nextInt();
				if (MAX_VALUE > 0 && MAX_VALUE <= 10000) {
					break;
				} else {
					System.out.println("��������ȷ�ķ�Χ!");
				}
			} else {
				System.out.println("����ȷ��������ֵ!");
			}
		}

		// ����Ҫ���ɵı��ʽ�ĸ���
		while (true) {
			System.out.println("������һ������0 С�ڵ���100�����������Զ����ɶ��ٸ����ʽ:");
			if (scanner.hasNextInt()) {
				NUMS = scanner.nextInt();
				if (NUMS > 0 && NUMS <= 100) {
					break;
				} else {
					System.out.println("��������ȷ�ķ�Χ!");
				}
			} else {
				System.out.println("����ȷ��������ֵ!");
			}
		}

	}

	/**
	 * @brief this function to output the result
	 * @return void
	 */
	public static void outputResult() {
		int i = 1;
		for (Rational re : listArr) {
			if (re.isRight()) {
				System.out.println("�� " + i + "������: ��ȷ��!" + re.getOpA()
						+ re.getCom() + re.getOpB() + " = " + re.getValue());
			} else {
				System.out.println("�� " + i + "������: �����!" + re.getOpA()
						+ re.getCom() + re.getOpB() + " = " + re.getValue());
			}

			i++;
		}
	}

	/**
	 * @brief this function to get data from user ,than compute the result
	 * @return void
	 */
	public static void input() {
		int i = 0;
		System.out.println("������������");
		for (i = 0; i < listArr.size(); i++) {
			System.out.println("��ʣ��" + (listArr.size() - i) + "����");
			int result = 0;
			while (true) {
				if (scanner.hasNextInt()) {
					result = scanner.nextInt();
					break;
				} else {
					scanner.next();
					System.out.println("��������Ч��ֵ");
				}
			}

			listArr.get(i).computing(result); // ���㱾��Ľ��
		}
	}

	/**
	 * @brief to show the expressions which are generated automatically
	 * @return void
	 */
	public static void ouput() {
		for (Rational ra : listArr) {
			System.out.println(ra.getOpA() + ra.getCom() + ra.getOpB());
		}

	}

}

/**
 * @brief This is a class of model of expression
 * @author JackCai
 *
 */
class Rational {

	private int opA;
	/** <This is the first operator of one expression */
	private int opB;
	/** <This is the second operator of one expression */
	private String com;
	/** <The operator of this expression */
	private int value;
	/** <The right result of this expression */
	private boolean isRight = false;
	/** <to indicate whether right or not */
	private int userValue;

	/** <to indicate the result of user input */

	/**
	 * @brief This is the constructor of Rational
	 * @param [in] opA The first operator
	 * @param [in] opB The second operator
	 * @param [in] com The operator of this expression
	 * */
	public Rational(int opA, int opB, String com) {

		this.opA = opA;
		this.opB = opB;
		this.com = com;
		computeValue();
	}

	public boolean isRight() {
		return isRight;
	}

	public int getUserValue() {
		return userValue;
	}

	public int getOpA() {
		return opA;
	}

	public int getOpB() {
		return opB;
	}

	public String getCom() {
		return com;
	}

	public int getValue() {
		return value;
	}

	/**
	 * @brief This function to compute the right result
	 * @return void
	 * */
	public void computeValue() {
		switch (com) {
		case "+":
			value = opA + opB;
			break;
		case "-":
			value = opA - opB;
			break;
		case "*":
			value = opA * opB;
			break;
		case "/":
			value = opA / opB;
			break;

		}
	}

	/***
	 * @brief to judge whether right or not of user input
	 * @param [in] value
	 * @return void
	 */
	public void computing(int value) {
		this.userValue = value;
		if (userValue == this.value) {
			this.isRight = true;
		}
	}
}
