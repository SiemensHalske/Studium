# AI-Machine Learning Component

This section of the repository is dedicated to Artificial Intelligence and Machine Learning exercises and examples. It includes scripts for training models, datasets for input, and documentation for reference.

## Directory Structure

### `/data`

Contains datasets used for training and evaluating machine learning models.

- `/deprecated`: This subdirectory contains older datasets that are kept for historical reference.
- `dataset_0_train.csv`, `dataset_1_test.csv`, etc.: These CSV files represent different portions of the dataset used in training and testing ML models.
- `dialogs.csv`, `dialogs.txt`: Textual data potentially used for natural language processing tasks.
- `WikiQA-test.tsv`, `WikiQA-train.tsv`: QA datasets formatted in TSV (tab-separated values) for question answering models.
- `dataset_part1.parquet`, `dataset_part2.parquet`, etc.: Parquet files are efficient for storing and processing large datasets and are used here for performance-intensive ML tasks.

### `/doc`

Documentation files and reference materials related to the machine learning models.

- `Attention Is All You Need.pdf`: A research paper that may be related to the models used or a source of inspiration for the algorithms implemented.
- `DataAlchemist - Training Documentation.pdf`: Specific documentation detailing the training procedures and model configurations.
- `/Websites`: This folder might contain HTML files or web scrapers related to web data used in ML tasks.

### `/models`

The trained machine learning models and their serialized representations are stored here.

- `saved_model.pb`: A protobuf file that stores the trained model in a format that TensorFlow and other ML frameworks can use.

### `/src`

Source code for the machine learning algorithms and utilities.

- `alchemist_train.py`: The main script used to train the machine learning models.
- `alchemist_transformerBlock.py`: Contains the implementation of a transformer block, potentially related to models like BERT or GPT.
- `convert_dataset.py`: A utility script to convert datasets between different formats.
- `DataAlchemist.py`: This is a primary script that utilizes various functions and classes to oversee and coordinate the processes of data transformation and model inference.
- `example_answer_1.txt`, `example_prompt_1.txt`: Example files that might be used as templates or test cases for the ML models.
- `GraphTransformer.cs`: A C# script that could indicate an implementation of the Graph Transformer architecture for machine learning tasks involving graph data structures.

## Installation and Usage

To get started with the AI-Machine Learning exercises, you may need to set up a Python environment, install dependencies from a `requirements.txt` file (if available), and follow any specific setup instructions provided in the documentation.

For Python scripts, navigate to the `/src` directory and run the following (replacing `script_name` with the actual script you wish to execute):

```bash
python script_name.py
python3 script_name.py
```

For the C# implementation, ensure you have the .NET framework or .NET Core installed, and compile/run the C# files accordingly.

## Documentation

Refer to the /doc directory for detailed explanations and instructions on the models and their training regimen. It's recommended to review the provided PDFs for an in-depth understanding of the algorithms and their theoretical foundations.

## Contributing

Contributions to this project are welcome. Please ensure to follow the best practices for coding and documentation. For major changes, please open an issue first to discuss what you would like to change.

## License

This repository is licensed under the MIT license. See `LICENSE` for details.
