import {Button, Form, Modal} from "react-bootstrap";

const JobsModal = ({selectedJob, setSelectedJob, showModal, setShowModal, fetchTable}) => {

    const handleChange = (e) => {
        const {name, value} = e.target;
        setSelectedJob(prev => ({
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
                        value={selectedJob.name}
                        onChange={handleChange}
                    />
                </Form.Group>
            </Form>
        )
    }

    const createSubmarine = async () => {
        try {
            const response = await fetch('http://localhost:8080/api/Jobs', {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(selectedJob)
            });

            if (!response.ok) {
                throw new Error(`Failed to update submarine: ${response.statusText}`);
            }

            const createdJob = await response.json();
            console.log("Created submarine successfully:", createdJob);
        }
        catch (error){
            console.error("Error updating submarine:", error);
        }
        setShowModal(false);
        fetchTable();
    }

    const updateSubmarine = async () => {
        console.log(selectedJob);

        if (!selectedJob.id) {
            console.error("Cannot update submarine without an ID");
            return;
        }

        try {
            const response = await fetch(`http://localhost:8080/api/Jobs/${selectedJob.id}`, {
                method: "PUT",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify(selectedJob),
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
                    {selectedJob.id ? "Edit Job" : "Create new Job"}
                </Modal.Title>
            </Modal.Header>
            <Modal.Body>
                {modalBody()}
            </Modal.Body>
            <Modal.Footer>
                { selectedJob.id ?
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
                        Create New Submarine
                    </Button>
                }
            </Modal.Footer>
        </Modal>
    )
}

export default JobsModal;