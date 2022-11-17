import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Game {
    public static final int MAX_FRAMES = 10;
    public static final int MAX_ROLL = 10;

    private final List<Frame> frames = new ArrayList<>();
    public int score() {
        return frames.stream().mapToInt(Frame::getTotal).sum();
    }

    public void roll(int pins) {
        Frame currentFrame = getCurrentFrame();
        currentFrame.roll(pins);
        Optional<Frame> lastFrame = getLastFrame();
        lastFrame.ifPresent(last -> last.setNext(currentFrame));
    }

    private Optional<Frame> getLastFrame() {
        if (frames.size() <= 1) {
            return Optional.empty();
        }
        return Optional.of(frames.get(frames.size() - 2));
    }

    private Frame getCurrentFrame() {
        if (frames.isEmpty()) {
            Frame frame = new Frame(1);
            frames.add(frame);
        }
        Frame currentFrame = frames.get(frames.size() - 1);
        if (currentFrame.isFull() && frames.size() < MAX_FRAMES) {
            Frame next = new Frame(frames.size() + 1);
            currentFrame = next;
            frames.add(next);
        }
        return currentFrame;
    }

    private static class Frame {
        final int num;

        public Frame(int num) {
            this.num = num;
        }
        int rollOne = -1;
        int rollTwo = -1;
        int rollThree = -1;
        private Frame bonus;


        void roll(int pins) {
            if (rollOne < 0) {
                rollOne = pins;
            } else if (rollTwo < 0) {
                rollTwo = pins;
            } else if (isLastFrame() && rollThree < 0) {
                rollThree = pins;
            }
        }

        boolean hasSpare() {
            if (isLastFrame()) return false;
            return !hasStrike() && rollOne + rollTwo == MAX_ROLL;
        }

        boolean hasStrike() {
            if (isLastFrame()) return false;
            return rollOne == MAX_ROLL || rollTwo == MAX_ROLL;
        }

        boolean isFull() {
            if (hasStrike()) return true;
            else if (isLastFrame()) {
                return rolledThree();
            }
            else return rolledTwo();
        }

        private boolean rolledTwo() {
            return rollOne >= 0 && rollTwo >= 0;
        }

        private boolean rolledThree() {
            return rollOne >= 0 && rollTwo >= 0 && rollThree >= 0;
        }

        int getTotal() {
            int total = getBaseTotal();
            if (isLastFrame()) return total + getRollThree();

            if (hasSpare()) {
                total += bonus.getRollOne();
            } else if (hasStrike() && bonus != null) {
                total += bonus.getNextTwoRolls();
            }
            return total;
        }

        private int getNextTwoRolls() {
            int total = getBaseTotal();
            if (hasStrike() && bonus != null) {
                total += bonus.getRollOne();
            }
            return total;
        }

        private boolean isLastFrame() {
            return num >= MAX_FRAMES;
        }

        private int getBaseTotal() {
            return getRollOne() + getRollTwo();
        }

        private int getRollOne() {
            return Math.max(0, rollOne);
        }

        private int getRollTwo() {
            return Math.max(0, rollTwo);
        }

        private int getRollThree() {
            return Math.max(0, rollThree);
        }


        public void setNext(Frame frame) {
            if (bonus == null) {
                this.bonus = frame;
            }
        }
    }
}
