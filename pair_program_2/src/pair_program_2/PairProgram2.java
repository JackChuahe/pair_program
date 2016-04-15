package pair_program_2;

import java.util.Scanner;

/**
 * 
 * @author JackCai
 * @brief 输入两个整数A B ,range in [-100 ,- 100]，若A B都小于 0，则输出 All less than 0
 *        ；否则就判断A B中的最大值y = max(A,B), y是否处于[25 ,65] 之间，若处于，则输出 25 <= max(A,B) <=
 *        65，否则输出 Illegal !
 *
 */
public class PairProgram2 {
	public static int opA;
	/** <opA number */
	public static int opB;
	/** <opB number */
	public static int maxValue;
	/** <maxValue */

	private final static int ZERO = 0;
	private final static int LEG_RANGE_LOW = 25;
	private final static int LEG_RANGE_HIGHT = 65;
	private final static int RANGE_LOW = -100;
	private final static int RANGE_HIGHT = 100;

	private static Scanner scanner = new Scanner(System.in);

	/**
	 * @brief this is the main function of this program
	 * @param [in] args
	 */
	public static void main(String args[]) {
		opA = getUserInput(); // ��ȡ������A
		opB = getUserInput(); // ��ȡ������B

		if (opA < ZERO && opB < ZERO) {
			System.out.println("All less than 0 !");
			return;
		}
		// otherwise
		maxValue = max(opA, opB); // ��ȡ���ߵ�MAXֵ
		if (maxValue >= LEG_RANGE_LOW && maxValue <= LEG_RANGE_HIGHT) { // ������
																		// [25,65]֮��Ļ��������
			System.out.println(LEG_RANGE_LOW + " <= max(" + opA + " , " + opB
					+ ") <= " + LEG_RANGE_HIGHT);
		} else {
			System.out.println("Illegal !"); // �������� Illegal !
		}
	}

	/**
	 * @brief this function to get the max value between A and B (PS: some fault
	 *        in these code)
	 * @param [in] opA
	 * @param [in] opB
	 * @return
	 */
	public static int max(int opA, int opB) {
		int temp = opA;
		if (opB > opA) {
			temp = opA;
		}
		return temp;
	}

	/**
	 * @brief this function to get input data from user
	 * @return int
	 */
	public static int getUserInput() {
		int value = -1;
		System.out
				.println("please enter a integer number,range in [-100,100]:");
		while (true) {
			if (scanner.hasNextInt()) {
				value = scanner.nextInt();
				if (value >= RANGE_LOW && value <= RANGE_HIGHT) {
					break;
				} else {
					System.out
							.println("Illegal range, should be in [-100,100],try again: ");
				}
			} else {
				scanner.nextLine(); // flush cache
				System.out.println("please enter right number:");
			}
		}
		return value;
	}
}
