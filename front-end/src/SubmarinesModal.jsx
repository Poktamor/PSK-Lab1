import {Button, Form, Modal} from "react-bootstrap";

const SubmarinesModal = ({selectedSubmarine, setSelectedSubmarine, showModal, setShowModal, fetchTable}) => {

    const handleChange = (e) => {
        const {name, value} = e.target;
        setSelectedSubmarine(prev => ({
            ...prev,
            [name]: value
        }));
    }

    const modalBody = () => {
        return (
            <Form>
                <Form.Group>
                    <Form.Label>Name</Form.Label>
                    <Form.Control
                        input="text"
                        name="name"
                        value={selectedSubmarine.name}
                        onChange={handleChange}
                    />
                </Form.Group>
            </Form>
        )
    }

    const createSubmarine = async () => {
        try {
            const response = await fetch('http://localhost:8080/api/Submarines', {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(selectedSubmarine)
            });

            if (!response.ok) {
                throw new Error(`Failed to update submarine: ${response.statusText}`);
            }

            const createdSubmarine = await response.json();
            console.log("Created submarine successfully:", createdSubmarine);
        }
        catch (error){
            console.error("Error updating submarine:", error);
        }
        setShowModal(false);
        fetchTable();
    }

    const updateSubmarine = async () => {
        console.log(selectedSubmarine);

        if (!selectedSubmarine.id) {
            console.error("Cannot update submarine without an ID");
            return;
        }

        try {
            const response = await fetch(`http://localhost:8080/api/Submarines/${selectedSubmarine.id}`, {
                method: "PUT",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify(selectedSubmarine),
            });

            if (!response.ok) {
                throw new Error(`Failed to update submarine: ${response.statusText}`);
            }

            const updatedPerson = await response.json();
            console.log("Submarine updated successfully:", updatedPerson);
        } catch (error) {
            console.error("Error updating submarine:", error);
        }
        setShowModal(false);
        fetchTable();
    };

    return (
        <Modal
            show={showModal}
            onHide={() => {setShowModal(false)}}
        >
            <Modal.Header closeButton>
                <Modal.Title>
                    {selectedSubmarine.id ? "Edit Person" : "Create new Person"}
                </Modal.Title>
            </Modal.Header>
            <Modal.Body>
                {modalBody()}
            </Modal.Body>
            <Modal.Footer>
                { selectedSubmarine.id ?
                    <>
                        <Button variant="danger">
                            Delete
                        </Button>
                        <Button variant="success" onClick={updateSubmarine}>
                            Save Changes
                        </Button>
                    </>
                    :
                    <Button onClick={createSubmarine}>
                        Create New Person
                    </Button>
                }
            </Modal.Footer>
        </Modal>
    )
}

export default SubmarinesModal;