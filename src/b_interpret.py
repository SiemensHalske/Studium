import sys

# Brainfuck code for "Hello World!" without using print statement in Python
bf_code = """
++++++++[>++++++++<-]>[<++++>-]
+<[>-<
    [>++++<-]>[<++++++++>-]<[>++++++++<-]
    +>[>

        ++++++++++[>+++++<-]>+.-.[-]<
    <[-]<->] <[>>

        +++++++[>+++++++<-]>.+++++.[-]<
<<-]] >[>

    ++++++++[>+++++++<-]>.[-]<
<-]<
+++++++++++[>+++>+++++++++>+++++++++>+<<<<-]>-.>-.+++++++.+++++++++++.<.
>>.++.+++++++..<-.>>-
[[-]<]
"""

# Brainfuck interpreter in Python
def brainfuck_interpreter(code):
    """
    Brainfuck interpreter that executes Brainfuck code and outputs result without using print.
    """
    code = [c for c in code if c in ['>', '<', '+', '-', '.', ',', '[', ']']]
    bracemap = build_bracemap(code)
    cells = [0] * 30000
    code_ptr = 0
    cell_ptr = 0
    output = []

    while code_ptr < len(code):
        command = code[code_ptr]

        if command == '>':
            cell_ptr += 1
        elif command == '<':
            cell_ptr -= 1

        elif command == '+':
            cells[cell_ptr] = (cells[cell_ptr] + 1) % 256

        elif command == '-':
            cells[cell_ptr] = (cells[cell_ptr] - 1) % 256

        elif command == '.':
            output.append(chr(cells[cell_ptr]))

        elif command == ',':
            pass  # No input functionality for this interpreter

        elif command == '[':
            if cells[cell_ptr] == 0:
                code_ptr = bracemap[code_ptr]

        elif command == ']':
            if cells[cell_ptr] != 0:
                code_ptr = bracemap[code_ptr]

        code_ptr += 1

    # Instead of print, we can use sys.stdout.write to output without newline
    sys.stdout.write(''.join(output))   
    sys.stdout.flush()

def build_bracemap(code):
    temp_bracestack, bracemap = [], {}
    for position, command in enumerate(code):
        if command == '[':
            temp_bracestack.append(position)
        if command == ']':
            start = temp_bracestack.pop()
            bracemap[start] = position
            bracemap[position] = start
    return bracemap

# Execute the Brainfuck interpreter with the "Hello World!" Brainfuck code
brainfuck_interpreter(bf_code)