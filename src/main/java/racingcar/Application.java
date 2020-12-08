package racingcar;

import java.util.Scanner;

public class Application {
	private static final String INPUT_NAME_MESSAGE = "경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)";
	private static final int INPUT_MAX_LENGTH = 5;
	private static final String ERROR_NAME_MESSAGE = "[ERROR] 5자초과에 이름은 가질수없으므로 삭제됩니다";
	private static final String INPUT_COUNT_MESSAGE = "시도할 회수는 몇회인가요?";
	private static final String ERROR_INPUT_COUNT_MESSAGE = "[ERROR] 시도 횟수는 숫자여야 한다.";
	private static final String GAME_START_MESSAGE = "\n실행 결과.";
	private static final String RESULT_MESSAGE = "최종 우승자: ";

	static int topPosition = 0;

	public static void main(String[] args) {
		final Scanner scanner = new Scanner(System.in);
		// TODO 援ы쁽 吏꾪뻾

		System.out.println(INPUT_NAME_MESSAGE);
		String input = scanner.nextLine();
		String[] inputCars = input.split(",");
		inputCars = checkInput(inputCars);

		Car[] cars = new Car[inputCars.length];
		cars = createCars(cars, inputCars);

		int count = checkCount(scanner);

		gameStart(cars, count);

	}

	public static String[] checkInput(String[] inputCars) {
		int carCount = 0;
		String[] beforeInputCars = new String[inputCars.length];

		for (int number = 0; number < inputCars.length; number++) {
			if (checkLength(inputCars[number])) {
				beforeInputCars[carCount] = inputCars[number];
				carCount++;
			}
		}

		String[] affterInputCars = new String[carCount];
		for (int beforeNumber = 0; beforeNumber < carCount; beforeNumber++) {
			affterInputCars[beforeNumber] = beforeInputCars[beforeNumber];
		}

		return affterInputCars;
	}

	public static Boolean checkLength(String inputCar) {
		if (inputCar.length() <= INPUT_MAX_LENGTH) {
			return true;
		}
		System.out.println(ERROR_NAME_MESSAGE);
		return false;
	}

	public static Car[] createCars(Car[] cars, String[] inputCars) {
		for (int carCount = 0; carCount < cars.length; carCount++) {
			Car car = new Car(inputCars[carCount]);
			cars[carCount] = car;
		}
		return cars;
	}

	public static int checkCount(Scanner scanner) {
		int count = 0;
		boolean checkNumber = false;
		while (!checkNumber) {
			try {
				System.out.println(INPUT_COUNT_MESSAGE);
				checkNumber = true;
				count = Integer.parseInt(scanner.nextLine());
			} catch (NumberFormatException e) {
				System.out.println(ERROR_INPUT_COUNT_MESSAGE);
				checkNumber = false;
			}
		}
		return count;
	}

	public static void gameStart(Car[] cars, int count) {
		System.out.println(GAME_START_MESSAGE);
		for (int move = 0; move < count; move++) {
			moveCar(cars);
			System.out.println();
		}

		resultGame(cars);
	}

	public static void moveCar(Car[] cars) {
		for (int carsCount = 0; carsCount < cars.length; carsCount++) {
			cars[carsCount].move();
			cars[carsCount].statusPrint();
		}
	}

	public static void resultGame(Car[] cars) {
		topPosition = 0;
		String resultMessage = "";

		for (int carsCount = 0; carsCount < cars.length; carsCount++) {
			resultMessage = winCheck(cars[carsCount], resultMessage);
		}

		topPosition = 0;
		System.out.println(RESULT_MESSAGE + " " + resultMessage);
	}

	public static String winCheck(Car car, String resultMessage) {

		if (topPosition < car.getPosition()) {
			topPosition = car.getPosition();
			return car.getName();
		}

		if (topPosition == car.getPosition()) {
			return resultMessage + ", " + car.getName();
		}

		return resultMessage;
	}
}
