from transformers import AutoTokenizer

def find_slicing_points(text, model_name="bert-base-uncased", max_tokens=512):
    tokenizer = AutoTokenizer.from_pretrained(model_name)
    positions = []
    current_pos = 0

    while current_pos < len(text):
        chunk = text[current_pos:]
        tokens = tokenizer.encode(chunk, add_special_tokens=False, truncation=True, max_length=max_tokens)

        last_token = tokenizer.decode(tokens[-1])
        last_token_pos = chunk.find(last_token) + len(last_token)
        next_pos = current_pos + last_token_pos

        positions.append(next_pos)
        current_pos = next_pos
        if current_pos >= len(text):
            break

    return positions

def tokenize_chunk(text, model_name="bert-base-uncased", max_tokens=512):
    tokenizer = AutoTokenizer.from_pretrained(model_name)
    tokens = tokenizer.encode(text, add_special_tokens=True, truncation=True, max_length=max_tokens)
    return tokens

def tokenize_text_in_chunks(text, model_name="bert-base-uncased", max_tokens=512):
    slicing_points = find_slicing_points(text, model_name, max_tokens)

    tokens_list = []
    current_pos = 0
    for slicing_point in slicing_points:
        chunk = text[current_pos:slicing_point]
        chunk_tokens = tokenize_chunk(chunk, model_name, max_tokens)
        tokens_list.append(chunk_tokens)
        current_pos = slicing_point

    return tokens_list

# Example usage
text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum. Curabitur pretium tincidunt lacus. Nulla gravida orci a odio. Nullam varius turpis et commodo pharetra est eros bibendum elit, in pellentesque velit justo ac purus. Suspendisse in felis. Etiam vel tortor sodales tellus ultricies commodo. Nunc pellentesque quam vel enim egestas pellentesque. Donec ut libero sed arcu vehicula ultricies a non tortor. Proin id risus fringilla eros sodales pellentesque. Quisque semper augue mattis wisi. Maecenas ligula. Pellentesque viverra vulputate enim. Aliquam erat volutpat. Phasellus tristique magna nec urna consequat, in pharetra sapien dignissim. Fusce convallis, mauris imperdiet gravida bibendum, nisl dolor hendrerit mauris, sed hendrerit enim diam eu ante. Nulla facilisi. Donec mollis hendrerit risus. Phasellus nec sem in justo pellentesque facilisis. Etiam imperdiet imperdiet orci. Nunc nec neque. Phasellus leo dolor, tempus non, auctor et, hendrerit quis, nisi. Curabitur ligula sapien, tincidunt non, euismod vitae, posuere imperdiet, leo. Maecenas malesuada. Praesent congue erat at massa. Sed cursus turpis vitae tortor. Donec posuere vulputate arcu. Phasellus accumsan cursus velit. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Sed aliquam, nisi quis porttitor congue, elit erat euismod orci, ac placerat dolor lectus quis orci. Phasellus consectetuer vestibulum elit. Aenean tellus metus, bibendum sed, posuere ac, mattis non, nunc."
token_lists = tokenize_text_in_chunks(text)
print(f"List of token lists: {token_lists}")
print(f"Number of chunks: {len(token_lists)}")
print(f"Number of tokens in each chunk: {[len(tokens) for tokens in token_lists]}")
print(f"Total number of tokens: {sum([len(tokens) for tokens in token_lists])}")
