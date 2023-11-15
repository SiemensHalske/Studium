from transformers import LongformerTokenizer, LongformerForSequenceClassification
import torch
from torch.utils.data import Dataset, DataLoader
import pandas as pd
import torch.optim as optim

# Paths to your dataset files
train_dataset_paths = [
    'C:\\Users\\Hendrik\\Documents\\Github\\DataAlchemist\\data\\dataset_part1.parquet',
    'C:\\Users\\Hendrik\\Documents\\Github\\DataAlchemist\\data\\dataset_part2.parquet',
    'C:\\Users\\Hendrik\\Documents\\Github\\DataAlchemist\\data\\dataset_part3.parquet'
]
val_dataset_path = 'C:\\Users\\Hendrik\\Documents\\Github\\DataAlchemist\\data\\dataset_part4.parquet'

# Initialize the tokenizer with a pre-trained Longformer model
tokenizer = LongformerTokenizer.from_pretrained('allenai/longformer-base-4096')

# Custom Dataset Class
class TextDataset(Dataset):
    def __init__(self, texts, labels, tokenizer, max_length=4096):
        self.encodings = tokenizer(texts, truncation=True, padding='max_length', max_length=max_length, return_tensors='pt')
        self.labels = labels

    def __len__(self):
        return len(self.labels)

    def __getitem__(self, idx):
        item = {key: val[idx] for key, val in self.encodings.items()}
        item['labels'] = torch.tensor(self.labels[idx])
        return item

# Function to load datasets and return DataLoader
def load_dataset(file_path, tokenizer):
    df = pd.read_parquet(file_path)
    prompts = df['prompt'].tolist()
    responses = df['response'].tolist()
    dataset = TextDataset(prompts, responses, tokenizer)
    loader = DataLoader(dataset, batch_size=1, shuffle=True)  # Adjust batch size as needed
    return loader

# Load the training and validation datasets
train_loaders = [load_dataset(path, tokenizer) for path in train_dataset_paths]
val_loader = load_dataset(val_dataset_path, tokenizer)

# Initialize the Longformer model for sequence classification
model = LongformerForSequenceClassification.from_pretrained('allenai/longformer-base-4096', num_labels=2)  # Update num_labels as needed
model.train()

# Define the optimizer
optimizer = optim.AdamW(model.parameters(), lr=1e-5)

# Training and validation loop
num_epochs = 3  # Define the number of epochs
for epoch in range(num_epochs):
    print(f"Epoch {epoch+1}/{num_epochs}")
    # Training loop
    for i, loader in enumerate(train_loaders):
        print(f"Training on dataset {i+1}/{len(train_loaders)}")
        for j, batch in enumerate(loader):
            optimizer.zero_grad()
            outputs = model(**{k: v.to(model.device) for k, v in batch.items()})
            loss = outputs.loss
            loss.backward()
            optimizer.step()
            print(f"Training loss for batch {j+1}/{len(loader)}: {loss.item()}")
    
    # Validation loop
    model.eval()
    val_loss = 0
    with torch.no_grad():
        for k, batch in enumerate(val_loader):
            outputs = model(**{k: v.to(model.device) for k, v in batch.items()})
            val_loss += outputs.loss.item()
            print(f"Validation loss for batch {k+1}/{len(val_loader)}: {outputs.loss.item()}")
    val_loss /= len(val_loader)
    print(f"Validation loss: {val_loss}")

# Save the trained model
model.save_pretrained('path/to/save/model')
