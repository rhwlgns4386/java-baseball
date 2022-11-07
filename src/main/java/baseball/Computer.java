package baseball;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.List;

public class Computer implements Observer {

    BaseBallNumber number;

    public void makeRandomNumber() {
        while (true) {
            try {
                List<Integer> numberList = randomNumberGenerate();
                number = BaseBallNumber.of(numberList);
                return;
            } catch (IllegalArgumentException e) {
            }
        }
    }

    private List<Integer> randomNumberGenerate() {
        List<Integer> numberList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            numberList.add(Randoms.pickNumberInRange(1, 9));
        }
        return numberList;
    }

    @Override
    public List<Hint> compare(List<Integer> list) {
        List<Integer> number = this.number.getNumber();
        List<Hint> hintList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            hintList.add(genHint(list, number, i, 0));
        }
        return hintList;
    }

    private Hint genHint(List<Integer> input, List<Integer> number, int inputIndex, int numberIndex) {
        if (isSame(numberIndex, BaseBallNumber.BASE_BALL_NUMBER_MAX_SIZE)) return Hint.NOTHING;

        if (isSame(input.get(inputIndex), number.get(numberIndex))) {
            return judgeByStrikeOrBall(inputIndex, numberIndex);
        }

        return genHint(input, number, inputIndex, numberIndex + 1);
    }

    private Hint judgeByStrikeOrBall(int inputIndex, int numberIndex) {
        if (isSame(inputIndex, numberIndex)) {
            return Hint.STRIKE;
        }

        return Hint.BALL;
    }

    private boolean isSame(Integer integer, Integer integer2) {
        return integer == integer2;
    }
}
