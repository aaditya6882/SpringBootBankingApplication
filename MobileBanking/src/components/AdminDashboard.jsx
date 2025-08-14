import CreateAccount from "./CreateAccount";
import Update from "./update.jsx";
import SearchUser from "./SearchAccount.jsx";
import {DepositWithDraw} from "./DepositWithDraw.jsx";
import {EnableDisableAccount} from "./EnableDisableAccount.jsx";
export default function AdminDashboard() {
    return (
        <div style={{ padding: "50px" }}>
            <h2>Admin Dashboard</h2>
            <CreateAccount />
            <Update />
            <SearchUser/>
            <DepositWithDraw/>
            <EnableDisableAccount/>
        </div>
    );
}
