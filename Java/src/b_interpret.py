import sys

def brainfuck_interpreter(code):
    """
    Updated Brainfuck interpreter that executes Brainfuck code and returns the output as a string.
    """
    # Filtering the code to include only Brainfuck commands
    code = [c for c in code if c in ['>', '<', '+', '-', '.', ',', '[', ']']]

    # Building the bracket map for loop handling
    bracemap = build_bracemap(code)

    # Initializing the cells (memory tape)
    cells = [0] * 30000
    code_ptr = 0
    cell_ptr = 0
    output = []

    while code_ptr < len(code):
        command = code[code_ptr]

        if command == '>':
            cell_ptr = (cell_ptr + 1) % 30000  # Circular tape to avoid out-of-range errors

        elif command == '<':
            cell_ptr = (cell_ptr - 1) % 30000  # Circular tape

        elif command == '+':
            cells[cell_ptr] = (cells[cell_ptr] + 1) % 256  # 8-bit cells

        elif command == '-':
            cells[cell_ptr] = (cells[cell_ptr] - 1) % 256  # 8-bit cells

        elif command == '.':
            output.append(chr(cells[cell_ptr]))  # Append the ASCII character to the output

        elif command == ',':
            # Input is not implemented in this interpreter
            pass

        elif command == '[' and cells[cell_ptr] == 0:
            code_ptr = bracemap[code_ptr]  # Jump forward to the matching ']'

        elif command == ']' and cells[cell_ptr] != 0:
            code_ptr = bracemap[code_ptr]  # Jump back to the matching '['

        code_ptr += 1

    return ''.join(output)

def build_bracemap(code):
    temp_bracestack, bracemap = [], {}
    for position, command in enumerate(code):
        if command == '[':
            temp_bracestack.append(position)
        elif command == ']':
            start = temp_bracestack.pop()
            bracemap[start] = position
            bracemap[position] = start
    return bracemap

# Example usage
bf_code = """
++++++++++
 [
  >+++++++>++++++++++>+++>+<<<<-
 ]                       Schleife zur Vorbereitung der Textausgabe
 >++.                    Ausgabe von 'H'
 >+.                     Ausgabe von 'e'
 +++++++.                'l'
 .                       'l'
 +++.                    'o'
 >++.                    Leerzeichen
 <<+++++++++++++++.      'W'
 >.                      'o'
 +++.                    'r'
 ------.                 'l'
 --------.               'd'
 >+.                     '!'
 >.                      Zeilenvorschub
 +++.                    Wagenrücklauf
"""
output = brainfuck_interpreter(bf_code)
print(output)  # Should print "Hello"
