from transformers import AutoTokenizer

def find_slicing_points(text, model_name="bert-base-uncased", max_tokens=512):
    tokenizer = AutoTokenizer.from_pretrained(model_name)

    positions = []
    current_pos = 0

    while current_pos < len(text):
        # Take a chunk of text from the current position and tokenize it
        chunk = text[current_pos:]
        tokens = tokenizer.encode(chunk, add_special_tokens=False, truncation=True, max_length=max_tokens)

        # Find the last token's end position in the original text
        last_token = tokenizer.decode(tokens[-1])
        last_token_pos = chunk.find(last_token) + len(last_token)
        next_pos = current_pos + last_token_pos

        # Add the position to the list and update the current position
        positions.append(next_pos)
        
        current_pos = next_pos
        if current_pos >= len(text):
            break

    return positions

# Example usage
text = "I know what to think about this. And now it's time for something completely different. I know what to think about this. And now it's time for something completely different. I know what to think about this. And now it's time for something completely different. I know what to think about this. And now it's time for something completely different. I know what to think about this. And now it's time for something completely different. I know what to think about this. And now it's time for something completely different."
slicing_points = find_slicing_points(text)
print(f"Slicing points: {slicing_points}")
