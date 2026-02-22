import {Button, Form, Modal} from "react-bootstrap";

const PeopleModal = ({selectedPerson, setSelectedPerson, showModal, setShowModal, fetchTable}) => {

    const updatePerson = async () => {
        console.log(selectedPerson);

        if (!selectedPerson.id) {
            console.error("Cannot update person without an ID");
            return;
        }

        try {
            const response = await fetch(`http://localhost:8080/api/People/${selectedPerson.id}`, {
                method: "PUT",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify(selectedPerson),
            });

            if (!response.ok) {
                throw new Error(`Failed to update person: ${response.statusText}`);
            }

            const updatedPerson = await response.json();
            console.log("Person updated successfully:", updatedPerson);
        } catch (error) {
            console.error("Error updating person:", error);
        }
        setShowModal(false);
        fetchTable();
    };

    const createPerson = async () => {
        try {
            const response = await fetch('http://localhost:8080/api/People', {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(selectedPerson)
            });

            if (!response.ok) {
                throw new Error(`Failed to update person: ${response.statusText}`);
            }

            const createdPerson = await response.json();
            console.log("Created person successfully:", createdPerson);
        }
        catch (error){
            console.error("Error updating person:", error);
        }
        setShowModal(false);
        fetchTable();
    }

    const handleChange = (e) => {
        const { name, value } = e.target;
        setSelectedPerson(prev => ({
            ...prev,
            [name]: value
        }));
    };

    const modalBody = () => {
        return(
            <Form>
                <Form.Group>
                    <Form.Label>Name</Form.Label>
                    <Form.Control
                        type="text"
                        name="name"
                        value={selectedPerson.name || ''}
                        onChange={handleChange}
                    />
                </Form.Group>
                <Form.Group>
                    <Form.Label>Surname</Form.Label>
                    <Form.Control
                        type="text"
                        name="surname"
                        value={selectedPerson.surname || ''}
                        onChange={handleChange}
                    />
                </Form.Group>
                <Form.Group>
                    <Form.Label>Job IDs</Form.Label>
                </Form.Group>
                <Form.Group>
                    <Form.Label>Submarine Id</Form.Label>
                    <Form.Control
                        type="number"
                        name="submarineId"
                        value={selectedPerson.submarineId || 0}
                        onChange={handleChange}
                    />
                </Form.Group>
            </Form>
        )
    }

    return (
        <Modal
            show={showModal}
            onHide={() => {setShowModal(false)}}
        >
            <Modal.Header closeButton>
                <Modal.Title>
                    {selectedPerson.id ? "Edit Person" : "Create new Person"}
                </Modal.Title>
            </Modal.Header>
            <Modal.Body>
                {modalBody()}
            </Modal.Body>
            <Modal.Footer>
                { selectedPerson.id ?
                    <>
                        <Button variant="danger">
                            Delete
                        </Button>
                        <Button variant="success" onClick={updatePerson}>
                            Save Changes
                        </Button>
                    </>
                    :
                    <Button onClick={createPerson}>
                        Create New Person
                    </Button>
                }
            </Modal.Footer>
        </Modal>
    )
}

export default PeopleModal;