from transformers import AutoModelForSequenceClassification, AutoTokenizer
import torch

# Load the model and tokenizer
model_name = "mayapapaya/Sentiment-Analyzer"
model = AutoModelForSequenceClassification.from_pretrained(model_name)
tokenizer = AutoTokenizer.from_pretrained("bert-base-uncased")

def sentiment_analysis(text):
    # Encode the text using the tokenizer
    inputs = tokenizer(text, return_tensors="pt", truncation=True, max_length=512)

    # Get model predictions
    with torch.no_grad():
        outputs = model(**inputs)

    # Interpret the results
    probabilities = torch.nn.functional.softmax(outputs.logits, dim=-1)
    sentiment = probabilities.argmax().item()

    # Map the sentiment to human-readable text
    sentiments = ["Negative", "Neutral", "Positive"]
    return sentiments[sentiment]

# Example usage
text = "I know what to think about this."
result = sentiment_analysis(text)
print(f"Sentiment: {result}")