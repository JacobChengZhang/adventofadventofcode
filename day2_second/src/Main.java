public class Main {
    // assume that the puzzleInput is valid
    static final String puzzleInput = "LLRRLLRLDDUURLLRDUUUDULUDLUULDRDDDULLLRDDLLLRRDDRRUDDURDURLRDDULRRRLLULLULLRUULDLDDDUUURRRRURURDUDLLRRLDLLRRDRDLLLDDRRLUDDLDDLRDRDRDDRUDDRUUURLDUDRRLULLLDRDRRDLLRRLDLDRRRRLURLLURLRDLLRUDDRLRDRRURLDULURDLUUDURLDRURDRDLULLLLDUDRLLURRLRURUURDRRRULLRULLDRRDDDULDURDRDDRDUDUDRURRRRUUURRDUUDUDDDLRRUUDDUUDDDUDLDRDLRDUULLRUUDRRRDURLDDDLDLUULUDLLRDUDDDDLDURRRDRLLRUUUUDRLULLUUDRLLRDLURLURUDURULUDULUDURUDDULDLDLRRUUDRDDDRLLRRRRLDRRRD\n" +
            "DRRRDULLRURUDRLRDLRULRRLRLDLUDLUURUUURURULRLRUDRURRRLLUDRLLDUDULLUUDLLUUUDDRLRUDDDDLDDUUDULDRRRDULUULDULDRUUULRUDDDUDRRLRLUDDURLLDRLUDUDURUUDRLUURRLUUUDUURUDURLUUUDRDRRRDRDRULLUURURDLUULLDUULUUDULLLDURLUDRURULDLDLRDRLRLUURDDRLDDLRRURUDLUDDDLDRLULLDRLLLURULLUURLUDDURRDDLDDDDRDUUULURDLUUULRRLRDLDRDDDRLLRUDULRRRUDRRLDRRUULUDDLLDUDDRLRRDLDDULLLRDURRURLLULURRLUULULRDLULLUUULRRRLRUDLRUUDDRLLLLLLLURLDRRUURLDULDLDDRLLLRDLLLDLRUUDRURDRDLUULDDRLLRRURRDULLULURRDULRUDUDRLUUDDDDUULDDDUUDURLRUDDULDDDDRUULUUDLUDDRDRD\n" +
            "RRRULLRULDRDLDUDRRDULLRLUUDLULLRUULULURDDDLLLULRURLLURUDLRDLURRRLRLDLLRRURUDLDLRULDDULLLUUDLDULLDRDLRUULDRLURRRRUDDLUDLDDRUDDUULLRLUUDLUDUDRLRUULURUDULDLUUDDRLLUUURRURUDDRURDLDRRDRULRRRRUUUDRDLUUDDDUDRLRLDRRRRUDDRLLRDRLUDRURDULUUURUULLRDUUULRULRULLRULRLUDUDDULURDDLLURRRULDRULDUUDDULDULDRLRUULDRDLDUDRDUDLURLLURRDLLDULLDRULDLLRDULLRURRDULUDLULRRUDDULRLDLDLLLDUDLURURRLUDRRURLDDURULDURRDUDUURURULLLUDDLDURURRURDDDRRDRURRUURRLDDLRRLDDULRLLLDDUDRULUULLULUULDRLURRRLRRRLDRRLULRLRLURDUULDDUDLLLUURRRLDLUDRLLLRRUU\n" +
            "URLDDDLDRDDDURRRLURRRRLULURLDDUDRDUDDLURURLLRDURDDRLRUURLDLLRDLRUUURLRLDLDRUDDDULLDULLDUULURLDRDUDRRLRRLULRDDULUDULDULLULDLRRLRRLLULRULDLLDULRRLDURRRRDLURDLUDUUUDLURRRRRUDDUDUUDULDLURRDRLRLUDUDUUDULDDURUDDRDRUDLRRUDRULDULRDRLDRUDRLLRUUDDRLURURDRRLRURULLDUUDRDLULRUULUDURRULLRLUUUUUDULRLUUDRDUUULLULUDUDDLLRRLDURRDDDLUDLUUDULUUULDLLLLUUDURRUDUDLULDRRRULLLURDURDDLRRULURUDURULRDRULLRURURRUDUULRULUUDDUDDUURLRLURRRRDLULRRLDRRDURUDURULULLRUURLLDRDRURLLLUUURUUDDDLDURRLLUUUUURLLDUDLRURUUUDLRLRRLRLDURURRURLULDLRDLUDDULLDUDLULLUUUDLRLDUURRR\n" +
            "RLLDRDURRUDULLURLRLLURUDDLULUULRRRDRLULDDRLUDRDURLUULDUDDDDDUDDDDLDUDRDRRLRLRLURDURRURDLURDURRUUULULLUURDLURDUURRDLDLDDUURDDURLDDDRUURLDURRURULURLRRLUDDUDDDLLULUDUUUDRULLLLULLRDDRDLRDRRDRRDLDLDDUURRRDDULRUUURUDRDDLRLRLRRDLDRDLLDRRDLLUULUDLLUDUUDRDLURRRRULDRDRUDULRLLLLRRULDLDUUUURLDULDDLLDDRLLURLUDULURRRUULURDRUDLRLLLRDDLULLDRURDDLLDUDRUDRLRRLULLDRRDULDLRDDRDUURDRRRLRDLDUDDDLLUDURRUUULLDRLUDLDRRRRDDDLLRRDUURURLRURRDUDUURRDRRUDRLURLUDDDLUDUDRDRURRDDDDRDLRUDRDRLLDULRURULULDRLRLRRLDURRRUL";

    int x = -2;
    int y = 0;

    Direction direction = null;

    enum Direction {
        U, R, D, L,
    }

    void calc() {
        String[] sequences = puzzleInput.split("\n");

        StringBuilder sb = new StringBuilder();
        for (String str : sequences) {
            x = -2;
            y = 0;


            for (int i = 0; i < str.length(); i++) {
                move(str.charAt(i));
            }


            sb.append(getChar(x, y));
        }

        System.out.println(sb.toString());
    }

    char getChar(int x, int y) {
        switch (x) {
            case 2: {
                switch (y) {
                    case 0: {
                        return '9';
                    }
                    default: {
                        System.out.println("Caught a bug!");
                        return '*';
                    }
                }
            }
            case 1: {
                switch (y) {
                    case 1: {
                        return '4';
                    }
                    case 0: {
                        return '8';
                    }
                    case -1: {
                        return 'C';
                    }
                    default: {
                        System.out.println("Caught a bug!");
                        return '*';
                    }
                }
            }
            case 0: {
                switch (y) {
                    case 2: {
                        return '1';
                    }
                    case 1: {
                        return '3';
                    }
                    case 0: {
                        return '7';
                    }
                    case -1: {
                        return 'B';
                    }
                    case -2: {
                        return 'D';
                    }
                    default: {
                        System.out.println("Caught a bug!");
                        return '*';
                    }
                }
            }
            case -1: {
                switch (y) {
                    case 1: {
                        return '2';
                    }
                    case 0: {
                        return '6';
                    }
                    case -1: {
                        return 'A';
                    }
                    default: {
                        System.out.println("Caught a bug!");
                        return '*';
                    }
                }
            }
            case -2: {
                switch (y) {
                    case 0: {
                        return '5';
                    }
                    default: {
                        System.out.println("Caught a bug!");
                        return '*';
                    }
                }
            }
            default: {
                System.out.println("Caught a bug!");
                return '*';
            }
        }
    }

    void move(int ch) {
        switch (ch) {
            case 'U': {
                if (Math.abs(x) + Math.abs(y + 1) <= 2) {
                    y++;
                }
                break;
            }
            case 'R': {
                if (Math.abs(x + 1) + Math.abs(y) <= 2) {
                    x++;
                }
                break;
            }
            case 'D': {
                if (Math.abs(x) + Math.abs(y - 1) <= 2) {
                    y--;
                }
                break;
            }
            case 'L': {
                if (Math.abs(x - 1) + Math.abs(y) <= 2) {
                    x--;
                }
                break;
            }
        }
    }

    public static void main(String[] args) {
        Main m = new Main();

        m.calc();
    }
}
